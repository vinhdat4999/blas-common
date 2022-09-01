package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.AuthUser;
import com.blas.blascommon.core.model.UserDetail;
import java.util.List;

public interface AuthUserService {

  public List<AuthUser> getAllAuthUser();

  public AuthUser getAuthUserByUserId(String userId);

  public AuthUser getAuthUserByUsername(String username);

  public AuthUser createUser(AuthUser authUser, UserDetail userDetail);

  public void updateAuthUser(AuthUser authUser);
}