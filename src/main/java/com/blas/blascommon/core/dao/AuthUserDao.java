package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserDao extends JpaRepository<AuthUser, String> {

    @Query("SELECT u FROM AuthUser u WHERE u.username = ?1")
    public AuthUser getAuthUserByUsername(String username);

}
