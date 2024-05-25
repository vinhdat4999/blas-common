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
@Document(collection = "centralized_logs")
public class CentralizedLog {

  @Id
  @Field(name = "centralized_log_id")
  @NotEmpty
  private String centralizedLogId;

  @Field(name = "global_id")
  @NotEmpty
  private String globalId;

  @Field(name = "log_time")
  @NotNull
  private LocalDateTime logTime;

  @Field(name = "service_name")
  private String serviceName;

  @Field(name = "log_type")
  private String logType;

  private String exception;

  private String message;

  private String cause;

  @Field(name = "log_data1")
  private String logData1;

  @Field(name = "log_data2")
  private String logData2;

  @Field(name = "log_data3")
  private String logData3;
}
