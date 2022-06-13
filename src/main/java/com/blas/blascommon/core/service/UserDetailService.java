package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.UserDetail;
import java.util.List;

public interface UserDetailService {

    public List<UserDetail> getAllUserDetail();

    public UserDetail getUserDetailByUserId(String UserId);

    public UserDetail getUserDetailByUsername(String username);

    public void updateUserDetail(UserDetail userDetail);

    public int countUserDetailByPhone(String phone);

    public int countUserDetailByEmail(String email);

}
