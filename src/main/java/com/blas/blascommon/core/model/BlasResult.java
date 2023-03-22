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

  @NotEmpty
  @Column(name = "export_time", nullable = false)
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
