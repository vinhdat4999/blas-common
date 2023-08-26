package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.UserDetail;
import java.util.List;

public interface UserDetailService {

  List<UserDetail> getAllUserDetail();

  UserDetail getUserDetailByUserId(String userId);

  UserDetail getUserDetailByUsername(String username);

  void updateUserDetail(UserDetail userDetail);

  UserDetail findUserDetailByPhone(String phone);

  UserDetail findUserDetailByEmail(String email);
}
