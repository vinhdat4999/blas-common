package com.blas.blascommon.core.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

  @ManyToOne
  @JoinColumn(name = "generated_by", foreignKey = @ForeignKey(name = "fk_email_logs_1"))
  @NotNull
  private AuthUser authUser;

  @Column(name = "time_log", nullable = false)
  @NotEmpty
  private LocalDateTime timeLog;

  @Column(name = "failed_email_num", nullable = false)
  @NotEmpty
  private int failedEmailNum;

  @Column(name = "failed_email_list")
  @NotEmpty
  private String failedEmailList;

  @Column(name = "sent_email_num", nullable = false)
  @NotEmpty
  private int sentEmailNum;

  @Column(name = "sent_email_list")
  @NotEmpty
  private String sentEmailList;
}
