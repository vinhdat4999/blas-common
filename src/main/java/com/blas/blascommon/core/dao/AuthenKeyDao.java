package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.AuthenKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenKeyDao extends JpaRepository<AuthenKey, String> {

    @Query("SELECT a FROM AuthenKey a WHERE a.authenKey = ?1")
    public AuthenKey getAuthenKeyByKey(String authenKey);

    @Query("SELECT a FROM AuthenKey a WHERE a.authUser.userId = ?1")
    public AuthenKey getAuthenKeyByUserId(String userId);

}