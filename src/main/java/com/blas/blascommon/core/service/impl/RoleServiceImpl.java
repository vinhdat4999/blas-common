package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.ROLE_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.RoleDao;
import com.blas.blascommon.core.model.Role;
import com.blas.blascommon.core.service.RoleService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleDao roleDao;

  @Override
  public Role getRoleByRoleId(String roleId) {
    Optional<Role> role = roleDao.findById(roleId);
    if (role.isEmpty()) {
      throw new NotFoundException(ROLE_ID_NOT_FOUND);
    }
    return role.get();
  }

  @Override
  public Role createRole(Role role) {
    role.setRoleId(genUUID());
    return roleDao.save(role);
  }

  @Override
  public void updateRole(Role role) {
    Optional<Role> roleOld = roleDao.findById(role.getRoleId());
    if (roleOld.isEmpty()) {
      throw new NotFoundException(ROLE_ID_NOT_FOUND);
    }
    roleDao.save(role);
  }
}