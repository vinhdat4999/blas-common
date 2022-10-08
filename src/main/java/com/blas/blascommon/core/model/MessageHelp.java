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
@Table(name = "message_helps")
public class MessageHelp {

  @Id
  @Column(name = "id", length = 50, nullable = false)
  @NotEmpty
  private String id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ticket_id", foreignKey = @ForeignKey(name = "fk_message_helps_1"))
  @NotNull
  private Help ticketId;

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
}