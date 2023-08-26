package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.Role;

public interface RoleService {

  Role getRoleByRoleId(String roleId);

  Role createRole(Role role);

  void updateRole(Role role);
}
