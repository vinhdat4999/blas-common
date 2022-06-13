package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.DUPLICATED_EMAIL;
import static com.blas.blascommon.constants.Response.DUPLICATED_PHONE;
import static com.blas.blascommon.constants.Response.DUPLICATED_USERNAME;
import static com.blas.blascommon.constants.Response.USERNAME_NOT_FOUND;
import static com.blas.blascommon.constants.Response.USER_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.AuthUserDao;
import com.blas.blascommon.core.dao.UserDetailDao;
import com.blas.blascommon.core.model.AuthUser;
import com.blas.blascommon.core.model.UserDetail;
import com.blas.blascommon.core.service.AuthUserService;
import com.blas.blascommon.exceptions.types.BadRequestException;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserDao authUserDao;

    @Autowired
    private UserDetailDao userDetailDao;

    @Override
    public List<AuthUser> getAllAuthUser() {
        return authUserDao.findAll();
    }

    @Override
    public AuthUser getAuthUserByUserId(String userId) {
        Optional<AuthUser> authUser = authUserDao.findById(userId);
        if (authUser.isEmpty()) {
            throw new NotFoundException(USER_ID_NOT_FOUND);
        }
        return authUser.get();
    }

    @Override
    public AuthUser getAuthUserByUsername(String username) {
        AuthUser authUser = authUserDao.getAuthUserByUsername(username);
        if (authUser == null) {
            throw new NotFoundException(USERNAME_NOT_FOUND);
        }
        return authUser;
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
        if (authUserDao.findById(authUser.getUserId()).isEmpty()) {
            throw new NotFoundException(USER_ID_NOT_FOUND);
        }
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
