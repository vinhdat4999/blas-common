package com.blas.blascommon.core.model;

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
}
