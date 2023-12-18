package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.BlasConstant.ALERT_EMAIL_RECEIVER_LIST;
import static com.blas.blascommon.constants.ResponseMessage.CENTRALIZED_LOG_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;
import static com.blas.blascommon.utils.StringUtils.COMMA;
import static com.blas.blascommon.utils.StringUtils.safeTrim;

import com.blas.blascommon.core.dao.mongodb.CentralizedLogDao;
import com.blas.blascommon.core.model.CentralizedLog;
import com.blas.blascommon.core.service.BlasConfigService;
import com.blas.blascommon.core.service.CentralizedLogService;
import com.blas.blascommon.enums.LogType;
import com.blas.blascommon.exceptions.types.NotFoundException;
import com.blas.blascommon.utils.email.InternalEmail;
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

  private static final String INTERNAL_EMAIL_SUBJECT = "[BLAS][CENTRALIZED LOG]ERROR ALERT";

  @Lazy
  private final CentralizedLogDao centralizedLogDao;

  @Lazy
  private final InternalEmail internalEmail;

  @Lazy
  private final BlasConfigService blasConfigService;

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
        .map(centralizedLogDao::save)
        .orElseThrow(() -> new NotFoundException(CENTRALIZED_LOG_ID_NOT_FOUND));
  }

  private void sendAlertEmail(String serviceName, LogType logType, String exception) {
    String emailContent = String.format(
        "1 error have logged. </br>Service: %s</br>Log type: %s</br>Exception: %s", serviceName,
        logType, exception);
    Arrays.stream(blasConfigService.getConfigValueFromKey(ALERT_EMAIL_RECEIVER_LIST).split(COMMA))
        .forEach(email -> internalEmail.sendEmail(safeTrim(email), INTERNAL_EMAIL_SUBJECT,
            emailContent));
  }
}
