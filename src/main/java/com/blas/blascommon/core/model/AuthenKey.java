package com.blas.blascommon.core.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "authen_keys")
public class AuthenKey {

    @Id
    @Column(name = "authen_id", length = 50, nullable = false)
    @NotEmpty
    private String authenId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "	fk_authen_keys_1"))
    @NotNull
    private AuthUser authUser;

    @Column(name = "authen_key", length = 256, nullable = false)
    @NotEmpty
    private String authenKey;

    @Column(name = "is_used")
    private boolean isUsed;

    @Column(name = "time_generate")
    @NotEmpty
    private LocalDateTime timeGenerate;

    @Column(name = "time_used")
    private LocalDateTime timeUsed;

    public AuthenKey() {
    }

    public AuthenKey(String authenId, AuthUser authUser, String authenKey, boolean isUsed,
            LocalDateTime timeGenerate, LocalDateTime timeUsed) {
        this.authenId = authenId;
        this.authUser = authUser;
        this.authenKey = authenKey;
        this.isUsed = isUsed;
        this.timeGenerate = timeGenerate;
        this.timeUsed = timeUsed;
    }

    public String getAuthenId() {
        return authenId;
    }

    public void setAuthenId(String authenId) {
        this.authenId = authenId;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public String getAuthenKey() {
        return authenKey;
    }

    public void setAuthenKey(String authenKey) {
        this.authenKey = authenKey;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean used) {
        isUsed = used;
    }

    public LocalDateTime getTimeGenerate() {
        return timeGenerate;
    }

    public void setTimeGenerate(LocalDateTime timeGenerate) {
        this.timeGenerate = timeGenerate;
    }

    public LocalDateTime getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(LocalDateTime timeUsed) {
        this.timeUsed = timeUsed;
    }

    @Override
    public String toString() {
        return "AuthenKey{" +
                "authenId='" + authenId + '\'' +
                ", authUser=" + authUser +
                ", authenKey='" + authenKey + '\'' +
                ", isUsed=" + isUsed +
                ", timeGenerate=" + timeGenerate +
                ", timeUsed=" + timeUsed +
                '}';
    }
}