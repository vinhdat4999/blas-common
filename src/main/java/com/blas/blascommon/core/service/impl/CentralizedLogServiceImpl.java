package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.CENTRALIZED_LOG_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.CentralizedLogDao;
import com.blas.blascommon.core.model.CentralizedLog;
import com.blas.blascommon.core.service.CentralizedLogService;
import com.blas.blascommon.enums.LogType;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class CentralizedLogServiceImpl implements CentralizedLogService {

  @Autowired
  private CentralizedLogDao centralizedLogDao;

  @Override
  public CentralizedLog saveLog(String serviceName, LogType logType, String exception, String cause,
      String requestData1, String requestData2, String requestData3, String logContent) {
    CentralizedLog centralizedLog = new CentralizedLog();
    centralizedLog.setCentralizedLogId(genUUID());
    centralizedLog.setLogTime(LocalDateTime.now());
    centralizedLog.setServiceName(serviceName);
    centralizedLog.setLogType(logType.name());
    centralizedLog.setException(exception);
    centralizedLog.setCause(cause);
    centralizedLog.setRequestData1(requestData1);
    centralizedLog.setRequestData2(requestData2);
    centralizedLog.setRequestData3(requestData3);
    centralizedLog.setLogContent(logContent);
    return centralizedLogDao.save(centralizedLog);
  }

  @Override
  public CentralizedLog createCentralizedLog(CentralizedLog centralizedLog) {
    centralizedLog.setCentralizedLogId(genUUID());
    return centralizedLogDao.save(centralizedLog);
  }

  @Override
  public void updateCentralizedLog(CentralizedLog centralizedLog) {
    Optional<CentralizedLog> centralizedLogOld = centralizedLogDao.findById(
        centralizedLog.getCentralizedLogId());
    if (centralizedLogOld.isEmpty()) {
      throw new NotFoundException(CENTRALIZED_LOG_ID_NOT_FOUND);
    }
    centralizedLogDao.save(centralizedLog);
  }

}
