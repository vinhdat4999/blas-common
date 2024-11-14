package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.utils.idutils.IdUtils.genUniqueId;

import com.blas.blascommon.core.dao.jpa.EmailLogDao;
import com.blas.blascommon.core.model.EmailLog;
import com.blas.blascommon.core.service.EmailLogService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class EmailLogServiceImpl implements EmailLogService {

  @Lazy
  private final EmailLogDao emailLogDao;

  @Override
  public EmailLog createEmailLog(EmailLog emailLog) {
    emailLog.setEmailLogId(genUniqueId());
    return emailLogDao.save(emailLog);
  }

  @Override
  public EmailLog createEmailLog(EmailLog emailLog, boolean autoGenId) {
    if (autoGenId) {
      emailLog.setEmailLogId(genUniqueId());
    }
    return emailLogDao.save(emailLog);
  }

  @Override
  public Integer getNumOfSentEmailInDateOfUserId(String userId, LocalDate date) {
    return emailLogDao.getNumOfSentEmailInDateOfUserId(userId, date);
  }
}
