package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.BLAS_LOG_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.BlasResultDao;
import com.blas.blascommon.core.model.BlasResult;
import com.blas.blascommon.core.service.BlasResultService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class BlasResultServiceImpl implements BlasResultService {

  @Autowired
  private BlasResultDao blasResultDao;

  @Override
  public BlasResult getBlasResultByLogId(String logId) {
    Optional<BlasResult> blasResult = blasResultDao.findById(logId);
    if (blasResult.isEmpty()) {
      throw new NotFoundException(BLAS_LOG_ID_NOT_FOUND);
    }
    return blasResult.get();
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
    Optional<BlasResult> blasResultOld = blasResultDao.findById(blasResult.getLogId());
    if (blasResultOld.isEmpty()) {
      throw new NotFoundException(BLAS_LOG_ID_NOT_FOUND);
    }
    blasResultDao.save(blasResult);
  }
}
