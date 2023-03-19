package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.EmailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailLogDao extends JpaRepository<EmailLog, String> {

}
