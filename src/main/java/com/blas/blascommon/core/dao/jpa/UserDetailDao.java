package com.blas.blascommon.core.dao.jpa;

import com.blas.blascommon.core.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailDao extends JpaRepository<UserDetail, String> {

  @Query("SELECT u FROM UserDetail u WHERE u.authUser.username = :username")
  UserDetail getUserDetailByUsername(@Param("username") String username);

  @Query("SELECT u FROM UserDetail u WHERE u.phoneNumber = :phone")
  UserDetail getUserDetailByPhone(@Param("phone") String phone);

  @Query("SELECT u FROM UserDetail u WHERE u.email = :email")
  UserDetail getUserDetailByEmail(@Param("email") String email);
}
