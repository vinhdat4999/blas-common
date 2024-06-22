package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.ResponseMessage.DUPLICATED_EMAIL;
import static com.blas.blascommon.constants.ResponseMessage.DUPLICATED_PHONE;
import static com.blas.blascommon.constants.ResponseMessage.DUPLICATED_USERNAME;
import static com.blas.blascommon.constants.ResponseMessage.USER_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.jpa.AuthUserDao;
import com.blas.blascommon.core.dao.jpa.UserDetailDao;
import com.blas.blascommon.core.model.AuthUser;
import com.blas.blascommon.core.model.UserDetail;
import com.blas.blascommon.core.service.AuthUserService;
import com.blas.blascommon.exceptions.types.BadRequestException;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class AuthUserServiceImpl implements AuthUserService {

  @Lazy
  private final AuthUserDao authUserDao;

  @Lazy
  private final UserDetailDao userDetailDao;

  @Override
  public List<AuthUser> getAllAuthUser() {
    return authUserDao.findAll();
  }

  @Override
  public AuthUser getAuthUserByUserId(String userId) {
    return authUserDao.findById(userId).orElseThrow(() -> new NotFoundException(USER_ID_NOT_FOUND));
  }

  @Override
  public AuthUser getAuthUserByUsername(String username) {
    return authUserDao.getAuthUserByUsername(username);
  }

  @Override
  public AuthUser getAuthUserByEmail(String email) {
    return authUserDao.getAuthUserByEmail(email);
  }

  @Override
  public AuthUser createUser(AuthUser authUser, UserDetail userDetail) {
    if (authUserDao.getAuthUserByUsername(authUser.getUsername()) != null
        || userDetailDao.getUserDetailByUsername(authUser.getUsername()) != null) {
      throw new BadRequestException(DUPLICATED_USERNAME);
    }
    if (userDetailDao.getUserDetailByPhone(userDetail.getPhoneNumber()) != null) {
      throw new BadRequestException(DUPLICATED_PHONE);
    }
    if (userDetailDao.getUserDetailByEmail(userDetail.getEmail()) != null) {
      throw new BadRequestException(DUPLICATED_EMAIL);
    }
    authUser.setUserId(genUUID());
    userDetail.setUserId(authUser.getUserId());
    authUser = authUserDao.save(authUser);
    userDetailDao.save(userDetail);
    return authUser;
  }

  @Override
  public void updateAuthUser(AuthUser authUser) {
    authUserDao.findById(authUser.getUserId())
        .orElseThrow(() -> new NotFoundException(USER_ID_NOT_FOUND));
    UserDetail userDetail = userDetailDao.getUserDetailByPhone(
        authUser.getUserDetail().getPhoneNumber());
    if (userDetail != null && !userDetail.getUserId().equals(authUser.getUserId())) {
      throw new BadRequestException(DUPLICATED_PHONE);
    }
    userDetail = userDetailDao.getUserDetailByEmail(authUser.getUserDetail().getEmail());
    if (userDetail != null && !userDetail.getUserId().equals(authUser.getUserId())) {
      throw new BadRequestException(DUPLICATED_EMAIL);
    }
    authUserDao.save(authUser);
  }
}
