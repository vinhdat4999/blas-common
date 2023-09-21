package com.blas.blascommon.core.dao.jpa;

import com.blas.blascommon.core.model.CentralizedLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentralizedLogDao extends JpaRepository<CentralizedLog, String> {

}
