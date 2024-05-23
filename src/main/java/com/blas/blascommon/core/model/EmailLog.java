package com.blas.blascommon.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "email_logs")
public class EmailLog {

  @Id
  @Column(name = "email_log_id", length = 50, nullable = false)
  @NotEmpty
  private String emailLogId;

  @Column(name = "global_id", length = 50, nullable = false)
  @NotEmpty
  private String globalId;

  @ManyToOne
  @JoinColumn(name = "generated_by", foreignKey = @ForeignKey(name = "fk_email_logs_1"))
  @NotNull
  private AuthUser authUser;

  @Column(name = "time_log", nullable = false)
  @NotNull
  private LocalDateTime timeLog;

  @Column(name = "failed_email_num", nullable = false)
  @NotNull
  private int failedEmailNum;

  @Column(name = "failed_email_list")
  @NotNull
  private String failedEmailList;

  @Column(name = "sent_email_num", nullable = false)
  @NotNull
  private int sentEmailNum;

  @Column(name = "sent_email_list")
  @NotNull
  private String sentEmailList;
}
