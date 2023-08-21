package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.ROLE_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.RoleDao;
import com.blas.blascommon.core.model.Role;
import com.blas.blascommon.core.service.RoleService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class RoleServiceImpl implements RoleService {

  @Lazy
  private final RoleDao roleDao;

  public RoleServiceImpl(RoleDao roleDao) {
    this.roleDao = roleDao;
  }

  @Lazy
  @Override
  public Role getRoleByRoleId(String roleId) {
    return roleDao.findById(roleId).orElseThrow(() -> new NotFoundException(ROLE_ID_NOT_FOUND));
  }

  @Override
  public Role createRole(Role role) {
    role.setRoleId(genUUID());
    return roleDao.save(role);
  }

  @Override
  public void updateRole(Role role) {
    roleDao.findById(role.getRoleId()).orElseThrow(() -> new NotFoundException(ROLE_ID_NOT_FOUND));
    roleDao.save(role);
  }
}
