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
@Table(name = "helps")
public class Help {

  @Id
  @Column(name = "ticket_id", length = 50, nullable = false)
  @NotEmpty
  private String ticketId;

  @Column(name = "create_time", nullable = false)
  @NotNull
  private LocalDateTime createTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "create_by", foreignKey = @ForeignKey(name = "fk_helps_1"))
  @NotNull
  private UserDetail createBy;

  @Column(name = "update_time", nullable = false)
  @NotNull
  private LocalDateTime updateTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "update_by", foreignKey = @ForeignKey(name = "fk_helps_2"))
  @NotNull
  private UserDetail updateBy;

  @Column(name = "content", nullable = false)
  @NotEmpty
  private String content;

  @Column(name = "status", length = 20, nullable = false)
  @NotEmpty
  private String status;
}
