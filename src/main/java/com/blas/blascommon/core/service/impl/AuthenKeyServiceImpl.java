package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Configuration.MINUTE_TO_EXPIRED;
import static com.blas.blascommon.constants.Response.USER_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genMixID;
import static com.blas.blascommon.utils.IdUtils.genUUID;
import static com.blas.blascommon.utils.TimeUtils.getTimeNow;

import com.blas.blascommon.core.dao.AuthUserDao;
import com.blas.blascommon.core.dao.AuthenKeyDao;
import com.blas.blascommon.core.model.AuthUser;
import com.blas.blascommon.core.model.AuthenKey;
import com.blas.blascommon.core.service.AuthUserService;
import com.blas.blascommon.core.service.AuthenKeyService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import com.blas.blascommon.security.hash.Sha256Encoder;
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
    boolean isValid = true;
    if (authenKeyObject == null || authenKeyObject.isUsed() || getTimeNow().isAfter(
        authenKeyObject.getTimeGenerate().plusMinutes(MINUTE_TO_EXPIRED))) {
      isValid = false;
    }
    return isValid;
  }

  @Override
  public String createAuthenKey(AuthUser authUser) {
    AuthenKey authenKeyOld = authenKeyDao.getAuthenKeyByUserId(authUser.getUserId());
    String key = genMixID();
    if (authenKeyOld == null) {
      AuthenKey authenKey = new AuthenKey();
      authenKey.setAuthenId(genUUID());
      authenKey.setKey(sha256Encoder.encode(key));
      authenKey.setAuthUser(authUser);
      authenKey.setTimeGenerate(getTimeNow());
      authenKey.setUsed(false);
      authenKeyDao.save(authenKey);
    } else {
      authenKeyOld.setKey(sha256Encoder.encode(key));
      authenKeyOld.setAuthUser(authUser);
      authenKeyOld.setTimeGenerate(getTimeNow());
      authenKeyOld.setUsed(false);
      authenKeyOld.setTimeUsed(null);
      authenKeyDao.save(authenKeyOld);
    }
    return key;
  }

  @Override
  public void useAuthenKey(AuthUser authUser) {
    AuthenKey authenKeyOld = authenKeyDao.getAuthenKeyByUserId(authUser.getUserId());
    authenKeyOld.setUsed(true);
    authenKeyOld.setTimeUsed(getTimeNow());
    authenKeyDao.save(authenKeyOld);
  }
}