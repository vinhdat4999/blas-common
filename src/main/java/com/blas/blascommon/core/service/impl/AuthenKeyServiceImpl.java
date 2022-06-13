package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Configuration.MINUTE_TO_EXPIRED;
import static com.blas.blascommon.constants.Response.USER_ID_NOT_FOUND;
import static com.blas.blascommon.security.SecurityUtils.getUsernameLoggedIn;
import static com.blas.blascommon.utils.TimeUtils.getTimeNow;

import com.blas.blascommon.core.dao.AuthUserDao;
import com.blas.blascommon.core.dao.AuthenKeyDao;
import com.blas.blascommon.core.model.AuthenKey;
import com.blas.blascommon.core.service.AuthenKeyService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class AuthenKeyServiceImpl implements AuthenKeyService {

    @Autowired
    private AuthenKeyDao authenKeyDao;

    @Autowired
    private AuthUserDao authUserDao;

    @Override
    public AuthenKey getAuthenKeyByUserId(String userId) {
        if (authUserDao.findById(userId).isEmpty()) {
            throw new NotFoundException(USER_ID_NOT_FOUND);
        }
        return authenKeyDao.getAuthenKeyByUserId(userId);
    }

    @Override
    public boolean isValidAuthenKey(String userId, String authenKey, LocalDateTime timeCheck) {
        AuthenKey authenKeyObject = authenKeyDao.getAuthenKeyByKey(authenKey);
        if (authenKeyObject == null) {
            return false;
        }
        if (!authenKeyObject.getAuthUser().getUsername().equals(getUsernameLoggedIn())) {
            return false;
        }
        if (authenKeyObject.isIsUsed()) {
            return false;
        }
        if (getTimeNow().isAfter(
                authenKeyObject.getTimeGenerate().plusMinutes(MINUTE_TO_EXPIRED))) {
            return false;
        }
        return true;
    }

    @Override
    public AuthenKey createOrUpdateAuthenKey(AuthenKey authenKey) {
        return authenKeyDao.save(authenKey);
    }
}
