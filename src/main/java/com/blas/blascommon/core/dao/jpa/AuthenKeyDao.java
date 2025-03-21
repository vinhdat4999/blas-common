package com.blas.blascommon.core.dao.jpa;

import com.blas.blascommon.core.model.AuthenKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenKeyDao extends JpaRepository<AuthenKey, String> {

  @Query("SELECT a FROM AuthenKey a WHERE a.key = :key")
  AuthenKey getAuthenKeyByKey(@Param("key") String key);

  @Query("SELECT a FROM AuthenKey a WHERE a.authUser.userId = :userId")
  AuthenKey getAuthenKeyByUserId(@Param("userId") String userId);
}
