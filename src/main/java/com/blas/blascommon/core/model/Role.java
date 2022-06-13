package com.blas.blascommon.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "role_id", length = 50, nullable = false)
    @NotEmpty
    private String roleId;

    @Column(name = "role_name", length = 50, nullable = false)
    @NotEmpty
    private String roleName;

    @Column(name = "is_active")
    @NotEmpty
    private boolean isActive;

    public Role() {
    }

    public Role(String roleId, String roleName, boolean isActive) {
        super();
        this.roleId = roleId;
        this.roleName = roleName;
        this.isActive = isActive;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Role [roleId=" + roleId + ", roleName=" + roleName + ", isActive=" + isActive + "]";
    }
}
