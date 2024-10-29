package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.EmailLog;
import java.time.LocalDate;

public interface EmailLogService {

  EmailLog createEmailLog(EmailLog emailLog);

  EmailLog createEmailLog(EmailLog emailLog, boolean autoGenId);

  Integer getNumOfSentEmailInDateOfUserId(String userId, LocalDate date);
}
