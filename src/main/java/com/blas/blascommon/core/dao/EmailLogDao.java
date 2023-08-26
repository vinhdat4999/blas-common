package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.EmailLog;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailLogDao extends JpaRepository<EmailLog, String> {

  @Query(nativeQuery = true, value = "SELECT SUM(e.sent_email_num) FROM email_logs e WHERE e.generated_by = :userId AND date(e.time_log) = :date")
  public Integer getNumOfSentEmailInDateOfUserId(@Param("userId") String userId,
      @Param("date") LocalDate date);
}
