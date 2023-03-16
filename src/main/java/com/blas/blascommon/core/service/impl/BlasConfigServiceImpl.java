package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.BLAS_CONFIG_KEY_NOT_FOUND;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.blas.blascommon.core.dao.BlasConfigDao;
import com.blas.blascommon.core.model.BlasConfig;
import com.blas.blascommon.core.service.BlasConfigService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.Optional;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class BlasConfigServiceImpl implements BlasConfigService {

  @Autowired
  private BlasConfigDao blasConfigDao;

  @Override
  public String getConfigValueFromKey(String key) {
    Optional<BlasConfig> blasConfig = blasConfigDao.findById(key);
    if (blasConfig.isEmpty()) {
      throw new NotFoundException(BLAS_CONFIG_KEY_NOT_FOUND);
    }
    return blasConfig.get().getValue();
  }

  @Override
  public String transformValue(String rawStr) {
    StringBuilder stringBuilder = new StringBuilder();
    try {
      String processedStr = new String(Hex.decodeHex(rawStr.toCharArray()), UTF_8);
      for (int index = 0; index < processedStr.length(); index++) {
        if (index % 2 == 1) {
          stringBuilder.append(processedStr.charAt(index));
        }
      }
    } catch (DecoderException e) {
      e.printStackTrace();
    }
    return stringBuilder.toString();
  }

}
