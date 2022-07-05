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
import lombok.Data;

@Data
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
}