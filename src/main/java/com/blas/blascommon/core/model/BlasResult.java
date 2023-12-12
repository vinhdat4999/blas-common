package com.blas.blascommon.core.model;

import com.blas.blascommon.enums.ReportType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Document(collection = "blas_results")
public class BlasResult {

  @Id
  @NotEmpty
  private String id;

  @Field("export_time")
  @NotNull
  private LocalDateTime exportTime;

  @Field("user_id")
  @NotNull
  private String userId;

  private String report;

  @Field("report_type")
  private String reportType;

  public BlasResult(String id) {
    this.id = id;
  }

  public BlasResult(LocalDateTime timeNow, AuthUser authUser, String report,
      ReportType reportType) {
    this.exportTime = timeNow;
    this.userId = authUser.getUserId();
    this.report = report;
    this.reportType = reportType.name();
  }
}
