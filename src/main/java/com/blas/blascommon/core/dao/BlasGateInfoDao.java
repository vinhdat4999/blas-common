package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.BlasGateInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlasGateInfoDao extends JpaRepository<BlasGateInfo, String> {

}
