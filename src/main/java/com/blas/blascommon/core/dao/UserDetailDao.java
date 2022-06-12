package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailDao extends JpaRepository<UserDetail, Integer> {

}
