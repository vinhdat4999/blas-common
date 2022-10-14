package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.StatisticLog;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticLogDao extends JpaRepository<StatisticLog, String> {

  @Query(nativeQuery = true, value = "SELECT COUNT(1) FROM statistic_logs s WHERE s.report_by = ?1 AND date(s.export_time) = ?2")
  public int getNumOfReportInDateOfUserId(String userId, LocalDate date);
}