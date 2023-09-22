package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.AuthUser;
import com.blas.blascommon.core.model.UserDetail;
import java.util.List;

public interface AuthUserService {

  List<AuthUser> getAllAuthUser();

  AuthUser getAuthUserByUserId(String userId);

  AuthUser getAuthUserByUsername(String username);

  AuthUser getAuthUserByEmail(String email);

  AuthUser createUser(AuthUser authUser, UserDetail userDetail);

  void updateAuthUser(AuthUser authUser);
}
