package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.BlasGateInfoDao;
import com.blas.blascommon.core.model.BlasGateInfo;
import com.blas.blascommon.core.service.BlasGateInfoService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class BlasGateInfoServiceImpl implements BlasGateInfoService {

  @Lazy
  private final BlasGateInfoDao blasGateInfoDao;

  public BlasGateInfoServiceImpl(BlasGateInfoDao blasGateInfoDao) {
    this.blasGateInfoDao = blasGateInfoDao;
  }

  @Override
  public BlasGateInfo createBlasGateInfo(BlasGateInfo blasGateInfo) {
    blasGateInfo.setId(genUUID());
    return blasGateInfoDao.save(blasGateInfo);
  }
}
