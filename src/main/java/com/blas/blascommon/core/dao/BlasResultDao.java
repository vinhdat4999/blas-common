package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.BlasResult;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlasResultDao extends JpaRepository<BlasResult, String> {

  @Query(nativeQuery = true, value = "SELECT COUNT(1) FROM blas_results s WHERE s.report_by = :userId AND date(s.export_time) = :date")
  public int getNumOfReportInDateOfUserId(@Param("userId") String userId,
      @Param("date") LocalDate date);
}
