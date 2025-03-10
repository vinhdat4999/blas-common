package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Configuration.MINUTE_TO_EXPIRED;
import static com.blas.blascommon.constants.ResponseMessage.USER_ID_NOT_FOUND;
import static com.blas.blascommon.utils.idutils.IdUtils.genMixID;
import static com.blas.blascommon.utils.idutils.IdUtils.genUniqueId;

import com.blas.blascommon.core.dao.jpa.AuthenKeyDao;
import com.blas.blascommon.core.model.AuthUser;
import com.blas.blascommon.core.model.AuthenKey;
import com.blas.blascommon.core.service.AuthenKeyService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import com.blas.blascommon.security.hash.Sha256Encoder;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class AuthenKeyServiceImpl implements AuthenKeyService {

  @Lazy
  private final AuthenKeyDao authenKeyDao;

  @Lazy
  private final Sha256Encoder sha256Encoder;

  @Override
  public AuthenKey getAuthenKeyByUserId(String userId) {
    return authenKeyDao.findById(userId)
        .orElseThrow(() -> new NotFoundException(USER_ID_NOT_FOUND));
  }

  @Override
  public boolean isValidAuthenKey(String username, String authenKey, LocalDateTime timeCheck) {
    authenKey = sha256Encoder.encode(authenKey);
    AuthenKey authenKeyObject = authenKeyDao.getAuthenKeyByKey(authenKey);
    return authenKeyObject != null
        && StringUtils.equals(authenKeyObject.getAuthUser().getUsername(), username)
        && !authenKeyObject.isUsed()
        && !LocalDateTime.now()
        .isAfter(authenKeyObject.getTimeGenerate().plusMinutes(MINUTE_TO_EXPIRED));
  }

  @Override
  public String createAuthenKey(AuthUser authUser) {
    AuthenKey authenKeyOld = authenKeyDao.getAuthenKeyByUserId(authUser.getUserId());
    String key = genMixID();
    if (authenKeyOld == null) {
      authenKeyDao.save(
          AuthenKey.builder()
              .authenId(genUniqueId())
              .authUser(authUser)
              .key(sha256Encoder.encode(key))
              .isUsed(false)
              .timeGenerate(LocalDateTime.now())
              .build());
    } else {
      authenKeyOld.setKey(sha256Encoder.encode(key));
      authenKeyOld.setAuthUser(authUser);
      authenKeyOld.setTimeGenerate(LocalDateTime.now());
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
    authenKeyOld.setTimeUsed(LocalDateTime.now());
    authenKeyDao.save(authenKeyOld);
  }
}
