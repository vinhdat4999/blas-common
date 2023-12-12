package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Configuration.adminEmailList;
import static com.blas.blascommon.constants.ResponseMessage.CENTRALIZED_LOG_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.mongodb.CentralizedLogDao;
import com.blas.blascommon.core.model.CentralizedLog;
import com.blas.blascommon.core.service.CentralizedLogService;
import com.blas.blascommon.enums.LogType;
import com.blas.blascommon.exceptions.types.NotFoundException;
import com.blas.blascommon.utils.email.SendEmail;
import java.time.LocalDateTime;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class CentralizedLogServiceImpl implements CentralizedLogService {

  @Lazy
  private final CentralizedLogDao centralizedLogDao;

  @Override
  public CentralizedLog saveLog(String serviceName, LogType logType, String exception, String cause,
      String requestData1, String requestData2, String requestData3, String logContent,
      boolean isSendEmailAlert) {
    final String centralizedLogId = genUUID();
    CentralizedLog centralizedLog = CentralizedLog.builder()
        .centralizedLogId(centralizedLogId)
        .logTime(LocalDateTime.now())
        .serviceName(serviceName)
        .logType(logType.name())
        .exception(exception)
        .cause(cause)
        .requestData1(requestData1)
        .requestData2(requestData2)
        .requestData3(requestData3)
        .logContent(logContent)
        .build();
    if (isSendEmailAlert) {
      sendAlertEmail(serviceName, logType, exception);
    }
    log.error("Error logged. centralizedLogId: {}", centralizedLogId);
    return centralizedLogDao.save(centralizedLog);
  }

  @Override
  public CentralizedLog createCentralizedLog(CentralizedLog centralizedLog) {
    centralizedLog.setCentralizedLogId(genUUID());
    return centralizedLogDao.save(centralizedLog);
  }

  @Override
  public void updateCentralizedLog(CentralizedLog centralizedLog) {
    centralizedLogDao.findById(centralizedLog.getCentralizedLogId())
        .orElseThrow(() -> new NotFoundException(CENTRALIZED_LOG_ID_NOT_FOUND));
    centralizedLogDao.save(centralizedLog);
  }

  private void sendAlertEmail(String serviceName, LogType logType, String exception) {
    final String HTML_NEW_LINE = "</br>";
    StringBuilder emailContent = new StringBuilder();
    emailContent.append("1 error have logged. ").append(HTML_NEW_LINE).append("Service: ")
        .append(serviceName).append(HTML_NEW_LINE)
        .append("Log type: ")
        .append(logType)
        .append(HTML_NEW_LINE)
        .append("Exception: ")
        .append(exception);
    Arrays.stream(adminEmailList).forEach(email -> {
      SendEmail sendEmail = new SendEmail("[BLAS][CENTRALIZED LOG]ERROR ALERT",
          emailContent.toString(), email);
      Thread thread = new Thread(sendEmail);
      thread.start();
    });
  }
}
