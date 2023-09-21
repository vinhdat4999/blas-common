package com.blas.blascommon.core.dao.jpa;

import com.blas.blascommon.core.model.BlasConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlasConfigDao extends JpaRepository<BlasConfig, String> {

}
