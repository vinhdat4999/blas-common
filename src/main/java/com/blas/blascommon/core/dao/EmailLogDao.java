package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.EmailLog;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailLogDao extends JpaRepository<EmailLog, String> {

  @Query(nativeQuery = true, value = "SELECT SUM(e.sent_email_num) FROM email_logs e WHERE e.generated_by = ?1 AND date(e.time_log) = ?2")
  public Integer getNumOfSentEmailInDateOfUserId(String userId, LocalDate date);
}
