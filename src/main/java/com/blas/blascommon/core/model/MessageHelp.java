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
@Table(name = "message_helps")
public class MessageHelp {

    @Id
    @Column(name = "id", length = 50, nullable = false)
    @NotEmpty
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id", foreignKey = @ForeignKey(name = "fk_message_helps_1"))
    @NotNull
    private Help ticket_id;

    @NotEmpty
    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_by", foreignKey = @ForeignKey(name = "fk_message_helps_2"))
    @NotNull
    private UserDetail createBy;

    @NotEmpty
    @Column(name = "message", nullable = false)
    private String message;

    public MessageHelp() {
    }

    public MessageHelp(String id, Help ticket_id, LocalDateTime createTime, UserDetail createBy,
            String message) {
        this.id = id;
        this.ticket_id = ticket_id;
        this.createTime = createTime;
        this.createBy = createBy;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Help getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Help ticket_id) {
        this.ticket_id = ticket_id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageHelp{" +
                "id='" + id + '\'' +
                ", ticket_id=" + ticket_id +
                ", createTime=" + createTime +
                ", createBy=" + createBy +
                ", message='" + message + '\'' +
                '}';
    }
}
