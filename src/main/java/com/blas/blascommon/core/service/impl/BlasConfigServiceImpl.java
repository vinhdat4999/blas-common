package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.BLAS_CONFIG_KEY_NOT_FOUND;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.blas.blascommon.core.dao.BlasConfigDao;
import com.blas.blascommon.core.service.BlasConfigService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class BlasConfigServiceImpl implements BlasConfigService {

  @Lazy
  @Autowired
  private BlasConfigDao blasConfigDao;

  @Override
  public String getConfigValueFromKey(String key) {
    return blasConfigDao.findById(key)
        .orElseThrow(() -> new NotFoundException(BLAS_CONFIG_KEY_NOT_FOUND)).getValue();
  }

  @Override
  public String transformValue(String rawStr) throws DecoderException {
    StringBuilder stringBuilder = new StringBuilder();
    String processedStr = new String(Hex.decodeHex(rawStr.toCharArray()), UTF_8);
    for (int index = 0; index < processedStr.length(); index++) {
      if (index % 2 == 1) {
        stringBuilder.append(processedStr.charAt(index));
      }
    }
    return stringBuilder.toString();
  }

}
