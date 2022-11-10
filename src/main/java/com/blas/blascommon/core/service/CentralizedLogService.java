package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.CentralizedLog;
import com.blas.blascommon.enums.LogType;

public interface CentralizedLogService {

  public CentralizedLog saveLog(String serviceName, LogType logType, String exception, String cause,
      String requestData1, String requestData2, String requestData3, String logContent);

  public CentralizedLog createCentralizedLog(CentralizedLog centralizedLog);

  public void updateCentralizedLog(CentralizedLog centralizedLog);
}
