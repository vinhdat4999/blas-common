package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.CentralizedLog;

public interface CentralizedLogService {

  CentralizedLog saveLog(Exception exception);

  CentralizedLog saveLog(Exception exception, Object logData1, Object logData2, Object logData3);

  CentralizedLog saveLog(Exception exception, Object logData1, Object logData2, Object logData3,
      boolean sendEmail);

  CentralizedLog createCentralizedLog(CentralizedLog centralizedLog);

  void updateCentralizedLog(CentralizedLog centralizedLog);
}
