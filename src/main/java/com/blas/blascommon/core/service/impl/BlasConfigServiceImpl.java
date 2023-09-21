package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.ResponseMessage.BLAS_CONFIG_KEY_NOT_FOUND;

import com.blas.blascommon.core.dao.jpa.BlasConfigDao;
import com.blas.blascommon.core.service.BlasConfigService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class BlasConfigServiceImpl implements BlasConfigService {

  @Lazy
  private final BlasConfigDao blasConfigDao;

  @Override
  public String getConfigValueFromKey(String key) {
    return blasConfigDao.findById(key)
        .orElseThrow(() -> new NotFoundException(BLAS_CONFIG_KEY_NOT_FOUND)).getValue();
  }
}
