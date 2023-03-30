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
@Table(name = "blas_results")
public class BlasResult {

  @Id
  @Column(name = "log_id", length = 50, nullable = false)
  @NotEmpty
  private String logId;

  @Column(name = "export_time", nullable = false)
  @NotNull
  private LocalDateTime exportTime;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "report_by", foreignKey = @ForeignKey(name = "fk_blas_results_1"))
  @NotNull
  private AuthUser authUser;

  @Column(name = "report")
  private String report;

  @Column(name = "report_type")
  private String reportType;
}
