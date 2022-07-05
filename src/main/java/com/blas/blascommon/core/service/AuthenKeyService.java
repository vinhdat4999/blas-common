package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.AuthUser;
import com.blas.blascommon.core.model.AuthenKey;
import java.time.LocalDateTime;

public interface AuthenKeyService {

    public AuthenKey getAuthenKeyByUserId(String userId);

    public boolean isValidAuthenKey(String userId, String authenKey, LocalDateTime timeCheck);

    public String createAuthenKey(AuthUser authUser);

    public void useAuthenKey(AuthUser authUser);

}
