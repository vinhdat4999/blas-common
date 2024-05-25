package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.BlasConstant.ALERT_EMAIL_RECEIVER_LIST;
import static com.blas.blascommon.constants.MDCConstant.GLOBAL_ID;
import static com.blas.blascommon.constants.ResponseMessage.CENTRALIZED_LOG_ID_NOT_FOUND;
import static com.blas.blascommon.enums.EmailTemplate.ERROR_ALERT;
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
import com.blas.blascommon.utils.TemplateUtils;
import com.blas.blascommon.utils.email.InternalEmail;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
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

  @Lazy
  private final TemplateUtils templateUtils;

  @Value("${blas.service.serviceName}")
  private String serviceName;

  @Value("${blas.blas-idp.isSendEmailAlert}")
  private boolean isSendEmailAlert;

  @Override
  public CentralizedLog saveLog(Exception exception) {
    final String centralizedLogId = genUUID();
    CentralizedLog centralizedLog = CentralizedLog.builder()
        .centralizedLogId(centralizedLogId)
        .globalId(MDC.get(GLOBAL_ID))
        .logTime(LocalDateTime.now())
        .serviceName(serviceName)
        .logType(ERROR.name())
        .exception(exception.toString())
        .message(exception.getMessage())
        .cause(Optional.ofNullable(exception.getCause()).map(Throwable::toString).orElse(EMPTY))
        .build();
    if (isSendEmailAlert) {
      sendAlertEmail(centralizedLogId, serviceName, ERROR, exception.toString());
    }
    log.error("Error logged. centralizedLogId: {}", centralizedLogId);
    return centralizedLogDao.save(centralizedLog);
  }

  @Override
  public CentralizedLog saveLog(Exception exception, Object logData1, Object logData2,
      Object logData3) {
    return saveLogBase(exception, logData1, logData2, logData3, isSendEmailAlert);
  }

  @Override
  public CentralizedLog saveLog(Exception exception, Object logData1, Object logData2,
      Object logData3, boolean sendEmail) {
    return saveLogBase(exception, logData1, logData2, logData3, sendEmail);
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

  @SneakyThrows
  private void sendAlertEmail(String centralizedLogId, String serviceName, LogType logType,
      String exception) {
    String emailContent = templateUtils.generateHtmlContent(ERROR_ALERT,
        Map.ofEntries(Map.entry("level", logType.name()),
            Map.entry("centralizedLogId", centralizedLogId),
            Map.entry("service", serviceName),
            Map.entry("exception", exception)));
    Arrays.stream(blasConfigService.getConfigValueFromKey(ALERT_EMAIL_RECEIVER_LIST).split(COMMA))
        .forEach(email -> internalEmail.sendEmail(safeTrim(email), INTERNAL_EMAIL_SUBJECT,
            emailContent));
  }

  private CentralizedLog saveLogBase(Exception exception, Object logData1, Object logData2,
      Object logData3, boolean isSendEmailAlert) {
    final String centralizedLogId = genUUID();
    CentralizedLog centralizedLog = CentralizedLog.builder()
        .centralizedLogId(centralizedLogId)
        .globalId(MDC.get(GLOBAL_ID))
        .logTime(LocalDateTime.now())
        .serviceName(serviceName)
        .logType(ERROR.name())
        .exception(Arrays.stream(exception.getStackTrace())
            .map(Objects::toString)
            .collect(Collectors.joining("\n")))
        .message(exception.getMessage())
        .cause(Optional.ofNullable(exception.getCause()).map(Throwable::toString).orElse(EMPTY))
        .logData1(Optional.ofNullable(logData1).map(Object::toString).orElse(EMPTY))
        .logData2(Optional.ofNullable(logData2).map(Object::toString).orElse(EMPTY))
        .logData3(Optional.ofNullable(logData3).map(Object::toString).orElse(EMPTY))
        .build();
    if (isSendEmailAlert) {
      sendAlertEmail(centralizedLogId, serviceName, ERROR, String.valueOf(exception));
    }
    log.error("Error logged. centralizedLogId: {}", centralizedLogId);
    return centralizedLogDao.save(centralizedLog);
  }
}
