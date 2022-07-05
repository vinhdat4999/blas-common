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
}
