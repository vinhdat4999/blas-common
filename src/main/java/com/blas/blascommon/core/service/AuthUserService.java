package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.AuthUser;

public interface AuthUserService {

    public AuthUser getAuthUserByUsername(String username);

}
