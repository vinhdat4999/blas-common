package com.blas.blascommon.core.dao.jpa;

import com.blas.blascommon.core.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDao extends JpaRepository<Report, String> {

}
