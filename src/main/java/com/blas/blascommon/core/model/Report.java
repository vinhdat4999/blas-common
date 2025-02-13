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
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reports")
public class Report implements Serializable {

  @Id
  @Column(name = "report_code", length = 10, nullable = false)
  @NotEmpty
  private String reportCode;

  @Column(name = "report_type", length = 30, nullable = false)
  @NotEmpty
  private String reportType;

  @Field(name = "log_time")
  @NotNull
  private LocalDateTime logTime;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_reports_1"))
  @NotNull
  private AuthUser authUser;

  @Column(name = "content")
  private String content;
}
