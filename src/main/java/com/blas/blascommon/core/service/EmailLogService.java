package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.EmailLog;
import java.time.LocalDate;

public interface EmailLogService {

  public EmailLog createEmailLog(EmailLog emailLog);

  public Integer getNumOfSentEmailInDateOfUserId(String userId, LocalDate date);
}
