package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.BlasGateInfoDao;
import com.blas.blascommon.core.model.BlasGateInfo;
import com.blas.blascommon.core.service.BlasGateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class BlasGateInfoServiceImpl implements BlasGateInfoService {

  @Lazy
  @Autowired
  private BlasGateInfoDao blasGateInfoDao;

  @Override
  public BlasGateInfo createBlasGateInfo(BlasGateInfo blasGateInfo) {
    blasGateInfo.setId(genUUID());
    return blasGateInfoDao.save(blasGateInfo);
  }
}
