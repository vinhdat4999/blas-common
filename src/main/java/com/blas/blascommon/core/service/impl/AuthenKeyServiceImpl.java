package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Configuration.MINUTE_TO_EXPIRED;
import static com.blas.blascommon.constants.Response.USER_ID_NOT_FOUND;
import static com.blas.blascommon.security.SecurityUtils.getUserIdLoggedIn;
import static com.blas.blascommon.security.SecurityUtils.getUsernameLoggedIn;
import static com.blas.blascommon.utils.IdUtils.genMixID;
import static com.blas.blascommon.utils.IdUtils.genUUID;
import static com.blas.blascommon.utils.TimeUtils.getTimeNow;

import com.blas.blascommon.core.dao.AuthUserDao;
import com.blas.blascommon.core.dao.AuthenKeyDao;
import com.blas.blascommon.core.model.AuthUser;
import com.blas.blascommon.core.model.AuthenKey;
import com.blas.blascommon.core.service.AuthUserService;
import com.blas.blascommon.core.service.AuthenKeyService;
import com.blas.blascommon.exceptions.types.BadRequestException;
import com.blas.blascommon.exceptions.types.NotFoundException;
import com.blas.blascommon.security.hash.Sha256Encoder;
import java.time.LocalDateTime;
import java.util.Optional;
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

    @Autowired
    private Sha256Encoder sha256Encoder;

    @Autowired
    private AuthUserService authUserService;

    @Override
    public AuthenKey getAuthenKeyByUserId(String userId) {
        if (authUserDao.findById(userId).isEmpty()) {
            throw new NotFoundException(USER_ID_NOT_FOUND);
        }
        return authenKeyDao.getAuthenKeyByUserId(userId);
    }

    @Override
    public boolean isValidAuthenKey(String userId, String authenKey, LocalDateTime timeCheck) {
        authenKey = sha256Encoder.encode(authenKey);
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
    public String createAuthenKey() {
        Optional<AuthUser> authUser = authUserDao.findById(getUserIdLoggedIn(authUserService));
        if (authUser == null) {
            throw new BadRequestException(USER_ID_NOT_FOUND);
        }
        AuthenKey authenKeyOld = authenKeyDao.getAuthenKeyByUserId(authUser.get().getUserId());
        String key = genMixID();
        if (authenKeyOld == null) {
            AuthenKey authenKey = new AuthenKey();
            authenKey.setAuthenId(genUUID());
            authenKey.setAuthenKey(sha256Encoder.encode(key));
            authenKey.setAuthUser(authUser.get());
            authenKey.setTimeGenerate(getTimeNow());
            authenKey.setIsUsed(false);
            return key;
        }
        authenKeyOld.setAuthenKey(sha256Encoder.encode(key));
        authenKeyOld.setAuthenKey(genMixID());
        authenKeyOld.setAuthUser(authUser.get());
        authenKeyOld.setTimeGenerate(getTimeNow());
        authenKeyOld.setIsUsed(false);
        return key;
    }

    @Override
    public void useAuthenKey() {
        Optional<AuthUser> authUser = authUserDao.findById(getUserIdLoggedIn(authUserService));
        if (authUser == null) {
            throw new BadRequestException(USER_ID_NOT_FOUND);
        }
        AuthenKey authenKeyOld = authenKeyDao.getAuthenKeyByUserId(authUser.get().getUserId());
        authenKeyOld.setIsUsed(true);
        authenKeyOld.setTimeUsed(getTimeNow());
        authenKeyDao.save(authenKeyOld);
    }
}
