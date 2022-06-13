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
@Table(name = "notifications")
public class Notification {

    @Id
    @Column(name = "nofitication_id", length = 50, nullable = false)
    @NotEmpty
    private String nofiticationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_notifications_1"))
    @NotNull
    private UserDetail userDetail;

    @Column(name = "content", length = 200, nullable = false)
    @NotEmpty
    private String content;

    @Column(name = "time")
    @NotEmpty
    private LocalDateTime time;

    @Column(name = "link_ref", length = 200)
    private String linkRef;

    @Column(name = "is_read")
    @NotEmpty
    private boolean isRead;

    public Notification() {
    }

    public Notification(String nofiticationId, UserDetail userDetail, String content,
            LocalDateTime time, String linkRef, boolean isRead) {
        this.nofiticationId = nofiticationId;
        this.userDetail = userDetail;
        this.content = content;
        this.time = time;
        this.linkRef = linkRef;
        this.isRead = isRead;
    }

    public String getNofiticationId() {
        return nofiticationId;
    }

    public void setNofiticationId(String nofiticationId) {
        this.nofiticationId = nofiticationId;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getLinkRef() {
        return linkRef;
    }

    public void setLinkRef(String linkRef) {
        this.linkRef = linkRef;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "nofiticationId='" + nofiticationId + '\'' +
                ", userDetail=" + userDetail +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", linkRef='" + linkRef + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}