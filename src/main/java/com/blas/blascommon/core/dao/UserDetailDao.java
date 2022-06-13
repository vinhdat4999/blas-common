package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailDao extends JpaRepository<UserDetail, Integer> {

    @Query("SELECT u FROM UserDetail u WHERE u.authUser.username = ?1")
    public UserDetail getUserDetailByUsername(String username);

    @Query("SELECT COUNT(u.phoneNumber) FROM UserDetail u WHERE u.phoneNumber = ?1")
    public int countUserDetailByPhone(String phone);

    @Query("SELECT COUNT(u.email) FROM UserDetail u WHERE u.email = ?1")
    public int countUserDetailByEmail(String email);
}
