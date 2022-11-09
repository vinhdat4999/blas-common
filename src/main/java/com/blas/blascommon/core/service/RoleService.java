package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.Role;

public interface RoleService {

  public Role getRoleByRoleId(String roleId);

  public Role createRole(Role role);

  public void updateRole(Role role);
}
