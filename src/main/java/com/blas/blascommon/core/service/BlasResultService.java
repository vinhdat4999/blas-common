package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.BlasResult;
import java.time.LocalDate;

public interface BlasResultService {

  public BlasResult getBlasResultByLogId(String logId);

  public int getNumOfReportInDateOfUserId(String userId, LocalDate date);

  public BlasResult createBlasResult(BlasResult blasResult);

  public void updateBlasResult(BlasResult blasResult);
}
