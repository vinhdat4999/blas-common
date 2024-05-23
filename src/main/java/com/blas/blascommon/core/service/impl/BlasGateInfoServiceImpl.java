package com.blas.blascommon.core.service.impl;

import com.blas.blascommon.core.dao.mongodb.BlasGateInfoDao;
import com.blas.blascommon.core.model.BlasGateInfo;
import com.blas.blascommon.core.service.BlasGateInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class BlasGateInfoServiceImpl implements BlasGateInfoService {

  @Lazy
  private final BlasGateInfoDao blasGateInfoDao;

  @Override
  public BlasGateInfo createBlasGateInfo(BlasGateInfo blasGateInfo) {
    return blasGateInfoDao.save(blasGateInfo);
  }
}
