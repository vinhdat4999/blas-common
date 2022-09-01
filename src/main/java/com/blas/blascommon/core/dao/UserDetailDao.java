package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailDao extends JpaRepository<UserDetail, String> {

  @Query("SELECT u FROM UserDetail u WHERE u.authUser.username = ?1")
  public UserDetail getUserDetailByUsername(String username);

  @Query("SELECT u FROM UserDetail u WHERE u.phoneNumber = ?1")
  public UserDetail getUserDetailByPhone(String phone);

  @Query("SELECT u FROM UserDetail u WHERE u.email = ?1")
  public UserDetail getUserDetailByEmail(String email);
}