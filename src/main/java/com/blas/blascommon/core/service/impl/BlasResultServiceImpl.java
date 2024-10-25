package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.ResponseMessage.BLAS_LOG_ID_NOT_FOUND;

import com.blas.blascommon.core.dao.mongodb.BlasResultDao;
import com.blas.blascommon.core.model.BlasResult;
import com.blas.blascommon.core.service.BlasResultService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class BlasResultServiceImpl implements BlasResultService {

  @Lazy
  private final BlasResultDao blasResultDao;

  @Override
  public BlasResult getBlasResultByLogId(String logId) {
    return blasResultDao.findById(logId)
        .orElseThrow(() -> new NotFoundException(BLAS_LOG_ID_NOT_FOUND));
  }

  @Override
  public int getNumOfReportInDateOfUserId(String userId, LocalDate date) {
    return blasResultDao.findByUserIdAndExportTime(userId, LocalDateTime.of(date, LocalTime.MIN),
        LocalDateTime.of(date, LocalTime.MAX)).size();
  }

  @Override
  public BlasResult createBlasResult(BlasResult blasResult) {
    return blasResultDao.save(blasResult);
  }

  @Override
  public void updateBlasResult(BlasResult blasResult) {
    if (blasResultDao.existsById(blasResult.getId())) {
      blasResultDao.save(blasResult);
    }
    throw new NotFoundException(BLAS_LOG_ID_NOT_FOUND);
  }
}
