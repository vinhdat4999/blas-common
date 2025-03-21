package com.blas.blascommon.core.dao.jpa;

import com.blas.blascommon.core.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, String> {

}
