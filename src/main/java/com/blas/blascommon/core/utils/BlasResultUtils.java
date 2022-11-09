package com.blas.blascommon.core.utils;

import com.blas.blascommon.core.model.AuthUser;
import com.blas.blascommon.core.model.BlasResult;
import com.blas.blascommon.enums.ReportType;
import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BlasResultUtils {

  public BlasResult buildBlasResult(LocalDateTime timeNow, AuthUser authUser, String report,
      ReportType reportType) {
    BlasResult blasResult = new BlasResult();
    blasResult.setExportTime(timeNow);
    blasResult.setAuthUser(authUser);
    blasResult.setReport(report);
    blasResult.setReportType(reportType.name());
    return blasResult;
  }
}
