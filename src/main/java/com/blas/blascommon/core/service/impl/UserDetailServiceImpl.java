package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.ResponseMessage.DUPLICATED_EMAIL;
import static com.blas.blascommon.constants.ResponseMessage.DUPLICATED_PHONE;
import static com.blas.blascommon.constants.ResponseMessage.USERNAME_NOT_FOUND;
import static com.blas.blascommon.constants.ResponseMessage.USER_ID_NOT_FOUND;

import com.blas.blascommon.core.dao.jpa.UserDetailDao;
import com.blas.blascommon.core.model.UserDetail;
import com.blas.blascommon.core.service.UserDetailService;
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
public class UserDetailServiceImpl implements UserDetailService {

  @Lazy
  private final UserDetailDao userDetailDao;

  @Override
  public List<UserDetail> getAllUserDetail() {
    return userDetailDao.findAll();
  }

  @Override
  public UserDetail getUserDetailByUserId(String userId) {
    return userDetailDao.findById(userId)
        .orElseThrow(() -> new NotFoundException(USER_ID_NOT_FOUND));
  }

  @Override
  public UserDetail getUserDetailByUsername(String username) {
    UserDetail userDetail = userDetailDao.getUserDetailByUsername(username);
    if (userDetail == null) {
      throw new NotFoundException(USERNAME_NOT_FOUND);
    }
    return userDetail;
  }

  @Override
  public void updateUserDetail(UserDetail userDetail) {
    userDetailDao.findById(userDetail.getUserId())
        .orElseThrow(() -> new NotFoundException(USER_ID_NOT_FOUND));
    UserDetail userDetailOld = userDetailDao.getUserDetailByPhone(userDetail.getPhoneNumber());
    if (!userDetail.getUserId().equals(userDetailOld.getUserId())) {
      throw new BadRequestException(DUPLICATED_PHONE);
    }
    userDetailOld = userDetailDao.getUserDetailByEmail(userDetailOld.getEmail());
    if (!userDetail.getUserId().equals(userDetailOld.getUserId())) {
      throw new BadRequestException(DUPLICATED_EMAIL);
    }
    userDetailDao.save(userDetail);
  }

  @Override
  public UserDetail findUserDetailByPhone(String phone) {
    return userDetailDao.getUserDetailByPhone(phone);
  }

  @Override
  public UserDetail findUserDetailByEmail(String email) {
    return userDetailDao.getUserDetailByEmail(email);
  }
}
