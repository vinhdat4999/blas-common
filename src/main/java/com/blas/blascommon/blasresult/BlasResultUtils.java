package com.blas.blascommon.blasresult;

import com.blas.blascommon.core.model.AuthUser;
import com.blas.blascommon.core.model.BlasResult;
import com.blas.blascommon.enums.ReportType;
import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BlasResultUtils {

  public BlasResult buildBlasResult(LocalDateTime timeNow, AuthUser authUser, String report,
      ReportType reportType) {
    return BlasResult.builder()
        .exportTime(timeNow)
        .authUser(authUser)
        .report(report)
        .reportType(reportType.name())
        .build();
  }
}
