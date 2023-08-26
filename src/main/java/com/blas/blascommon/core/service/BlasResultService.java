package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.BlasResult;
import java.time.LocalDate;

public interface BlasResultService {

  BlasResult getBlasResultByLogId(String logId);

  int getNumOfReportInDateOfUserId(String userId, LocalDate date);

  BlasResult createBlasResult(BlasResult blasResult);

  void updateBlasResult(BlasResult blasResult);
}
