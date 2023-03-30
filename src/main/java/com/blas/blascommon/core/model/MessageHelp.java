package com.blas.blascommon.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

  @Column(name = "create_time", nullable = false)
  @NotNull
  private LocalDateTime createTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "create_by", foreignKey = @ForeignKey(name = "fk_message_helps_2"))
  @NotNull
  private UserDetail createBy;

  @Column(name = "message", nullable = false)
  @NotEmpty
  private String message;
}
