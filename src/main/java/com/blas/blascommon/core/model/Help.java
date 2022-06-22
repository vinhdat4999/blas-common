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
@Table(name = "helps")
public class Help {

    @Id
    @Column(name = "ticket_id", length = 50, nullable = false)
    @NotEmpty
    private String ticketId;

    @NotEmpty
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by", foreignKey = @ForeignKey(name = "fk_helps_1"))
    @NotNull
    private UserDetail createBy;

    @NotEmpty
    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_by", foreignKey = @ForeignKey(name = "fk_helps_2"))
    @NotNull
    private UserDetail updateBy;

    @NotEmpty
    @Column(name = "content", nullable = false)
    private String content;

    @NotEmpty
    @Column(name = "status", length = 20, nullable = false)
    private String status;

    public Help() {
    }

    public Help(String ticketId, LocalDateTime createTime, UserDetail createBy,
            LocalDateTime updateTime, UserDetail updateBy, String content, String status) {
        this.ticketId = ticketId;
        this.createTime = createTime;
        this.createBy = createBy;
        this.updateTime = updateTime;
        this.updateBy = updateBy;
        this.content = content;
        this.status = status;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public UserDetail getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UserDetail createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public UserDetail getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(UserDetail updateBy) {
        this.updateBy = updateBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Help{" +
                "ticketId='" + ticketId + '\'' +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", updateTime=" + updateTime +
                ", updateBy=" + updateBy +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
