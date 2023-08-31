package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.AuthUser;
import com.blas.blascommon.core.model.AuthenKey;
import java.time.LocalDateTime;

public interface AuthenKeyService {

  AuthenKey getAuthenKeyByUserId(String userId);

  boolean isValidAuthenKey(String username, String authenKey, LocalDateTime timeCheck);

  String createAuthenKey(AuthUser authUser);

  void useAuthenKey(AuthUser authUser);
}
