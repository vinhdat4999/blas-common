package com.blas.blascommon.core.dao.jpa;

import com.blas.blascommon.core.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserDao extends JpaRepository<AuthUser, String> {

  @Query("SELECT u FROM AuthUser u WHERE u.username = :username")
  AuthUser getAuthUserByUsername(@Param("username") String username);

  @Query("SELECT u FROM AuthUser u WHERE u.userDetail.email = :email")
  AuthUser getAuthUserByEmail(@Param("email") String email);
}
