package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.UserDetail;
import java.util.List;

public interface UserDetailService {

  public List<UserDetail> getAllUserDetail();

  public UserDetail getUserDetailByUserId(String userId);

  public UserDetail getUserDetailByUsername(String username);

  public void updateUserDetail(UserDetail userDetail);

  public UserDetail findUserDetailByPhone(String phone);

  public UserDetail findUserDetailByEmail(String email);
}
