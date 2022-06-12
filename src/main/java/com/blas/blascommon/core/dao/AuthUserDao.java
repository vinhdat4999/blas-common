package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.AuthUser;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AuthUserDao extends JpaRepository<AuthUser, Integer> {

    @Query("SELECT u FROM AuthUser u WHERE u.username = ?1")
    public AuthUser getAuthUserByUsername(String username);

}
