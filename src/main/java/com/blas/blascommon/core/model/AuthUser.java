package com.blas.blascommon.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "auth_users")
public class AuthUser {

    @Id
    @Column(name = "user_id", length = 50, nullable = false)
    @NotEmpty
    private String userId;

    @Column(name = "username", length = 20, nullable = false)
    @NotEmpty
    private String username;

    @OneToOne(mappedBy = "authUser")
    @NotNull
    private UserDetail userDetail;

    @Column(name = "password", length = 100, nullable = false)
    @NotEmpty
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_auth_users_1"))
    @NotNull
    private Role role;

    @Column(name = "count_login_failed")
    @NotEmpty
    private int countLoginFailed;

    @Column(name = "is_active")
    @NotEmpty
    private boolean isActive;

    public AuthUser() {
    }

    public AuthUser(String userId, String username, UserDetail userDetail, String password,
            Role role,
            int countLoginFailed, boolean isActive) {
        this.userId = userId;
        this.username = username;
        this.userDetail = userDetail;
        this.password = password;
        this.role = role;
        this.countLoginFailed = countLoginFailed;
        this.isActive = isActive;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getCountLoginFailed() {
        return countLoginFailed;
    }

    public void setCountLoginFailed(int countLoginFailed) {
        this.countLoginFailed = countLoginFailed;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", userDetail=" + userDetail +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", countLoginFailed=" + countLoginFailed +
                ", isActive=" + isActive +
                '}';
    }
}
