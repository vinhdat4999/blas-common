package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.DUPLICATED_EMAIL;
import static com.blas.blascommon.constants.Response.DUPLICATED_PHONE;
import static com.blas.blascommon.constants.Response.USERNAME_NOT_FOUND;
import static com.blas.blascommon.constants.Response.USER_ID_NOT_FOUND;

import com.blas.blascommon.core.dao.AuthUserDao;
import com.blas.blascommon.core.dao.UserDetailDao;
import com.blas.blascommon.core.model.UserDetail;
import com.blas.blascommon.core.service.UserDetailService;
import com.blas.blascommon.exceptions.types.BadRequestException;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private AuthUserDao authUserDao;

    @Autowired
    private UserDetailDao userDetailDao;

    @Override
    public List<UserDetail> getAllUserDetail() {
        return userDetailDao.findAll();
    }

    @Override
    public UserDetail getUserDetailByUserId(String userId) {
        Optional<UserDetail> userDetail = userDetailDao.findById(userId);
        if (userDetail.isEmpty()) {
            throw new NotFoundException(USER_ID_NOT_FOUND);
        }
        return userDetail.get();
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
        if (userDetailDao.findById(userDetail.getUserId()).isEmpty()) {
            throw new NotFoundException(USER_ID_NOT_FOUND);
        }
        UserDetail userDetailOld = userDetailDao.getUserDetailByPhone(userDetail.getPhoneNumber());
        if (userDetail != null && !userDetail.getUserId().equals(userDetailOld.getUserId())) {
            throw new BadRequestException(DUPLICATED_PHONE);
        }
        userDetailOld = userDetailDao.getUserDetailByEmail(userDetailOld.getEmail());
        if (userDetail != null && !userDetail.getUserId().equals(userDetailOld.getUserId())) {
            throw new BadRequestException(DUPLICATED_EMAIL);
        }
        userDetailDao.save(userDetail);
    }

    @Override
    public UserDetail findUserDetailByPhone(String phone) {
        UserDetail userDetail = userDetailDao.getUserDetailByPhone(phone);
        return userDetail;
    }

    @Override
    public UserDetail findUserDetailByEmail(String email) {
        UserDetail userDetail = userDetailDao.getUserDetailByEmail(email);
        return userDetail;
    }
}
