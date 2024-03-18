package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.BlasConstant.ALERT_EMAIL_RECEIVER_LIST;
import static com.blas.blascommon.constants.ResponseMessage.CENTRALIZED_LOG_ID_NOT_FOUND;
import static com.blas.blascommon.enums.LogType.ERROR;
import static com.blas.blascommon.utils.IdUtils.genUUID;
import static com.blas.blascommon.utils.StringUtils.COMMA;
import static com.blas.blascommon.utils.StringUtils.EMPTY;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class CentralizedLogServiceImpl implements CentralizedLogService {

  @Value("${blas.service.serviceName}")
  private String serviceName;

  @Value("${blas.blas-idp.isSendEmailAlert}")
  private boolean isSendEmailAlert;

  private static final String INTERNAL_EMAIL_SUBJECT = "[BLAS][CENTRALIZED LOG]ERROR ALERT";

  @Lazy
  private final CentralizedLogDao centralizedLogDao;

  @Lazy
  private final InternalEmail internalEmail;

  @Lazy
  private final BlasConfigService blasConfigService;

  public static void main(String[] args) {
    System.out.println(
        new JSONObject(Optional.ofNullable(null).map(Object::toString).orElse(EMPTY)));
  }

  @Override
  public CentralizedLog saveLog(Exception exception) {
    final String centralizedLogId = genUUID();
    CentralizedLog centralizedLog = CentralizedLog.builder()
        .centralizedLogId(centralizedLogId)
        .logTime(LocalDateTime.now())
        .serviceName(serviceName)
        .logType(ERROR.name())
        .exception(exception.toString())
        .cause(Optional.ofNullable(exception.getCause()).map(Throwable::toString).orElse(EMPTY))
        .build();
    if (isSendEmailAlert) {
      sendAlertEmail(serviceName, ERROR, exception.toString());
    }
    log.error("Error logged. centralizedLogId: {}", centralizedLogId);
    return centralizedLogDao.save(centralizedLog);
  }

  @Override
  public CentralizedLog saveLog(Exception exception, Object logData1, Object logData2,
      Object logData3) {
    final String centralizedLogId = genUUID();
    CentralizedLog centralizedLog = CentralizedLog.builder()
        .centralizedLogId(centralizedLogId)
        .logTime(LocalDateTime.now())
        .serviceName(serviceName)
        .logType(ERROR.name())
        .exception(Arrays.stream(exception.getStackTrace())
            .map(Objects::toString)
            .collect(Collectors.joining("\n")))
        .cause(Optional.ofNullable(exception.getCause()).map(Throwable::toString).orElse(EMPTY))
        .logData1(Optional.ofNullable(logData1).map(Object::toString).orElse(EMPTY))
        .logData2(Optional.ofNullable(logData2).map(Object::toString).orElse(EMPTY))
        .logData3(Optional.ofNullable(logData3).map(Object::toString).orElse(EMPTY))
        .build();
    if (isSendEmailAlert) {
      sendAlertEmail(serviceName, ERROR, exception.toString());
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
