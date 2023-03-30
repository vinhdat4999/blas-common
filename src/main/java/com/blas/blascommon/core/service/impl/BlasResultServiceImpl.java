package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.BLAS_LOG_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.BlasResultDao;
import com.blas.blascommon.core.model.BlasResult;
import com.blas.blascommon.core.service.BlasResultService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class BlasResultServiceImpl implements BlasResultService {

  @Lazy
  @Autowired
  private BlasResultDao blasResultDao;

  @Override
  public BlasResult getBlasResultByLogId(String logId) {
    return blasResultDao.findById(logId)
        .orElseThrow(() -> new NotFoundException(BLAS_LOG_ID_NOT_FOUND));
  }

  @Override
  public int getNumOfReportInDateOfUserId(String userId, LocalDate date) {
    return blasResultDao.getNumOfReportInDateOfUserId(userId, date);
  }

  @Override
  public BlasResult createBlasResult(BlasResult blasResult) {
    blasResult.setLogId(genUUID());
    return blasResultDao.save(blasResult);
  }

  @Override
  public void updateBlasResult(BlasResult blasResult) {
    blasResultDao.findById(blasResult.getLogId())
        .orElseThrow(() -> new NotFoundException(BLAS_LOG_ID_NOT_FOUND));
    blasResultDao.save(blasResult);
  }
}
