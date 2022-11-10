package com.blas.blascommon.core.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "centralized_logs")
public class CentralizedLog {

  @Id
  @Column(name = "centralized_log_id", length = 50, nullable = false)
  @NotEmpty
  private String centralizedLogId;

  @NotEmpty
  @Column(name = "log_time", nullable = false)
  private LocalDateTime logTime;

  @Column(name = "service_name", nullable = false)
  private String serviceName;

  @Column(name = "log_type")
  private String logType;

  @Column(name = "exception")
  private String exception;

  @Column(name = "cause")
  private String cause;

  @Column(name = "request_data1")
  private String requestData1;

  @Column(name = "request_data2")
  private String requestData2;

  @Column(name = "request_data3")
  private String requestData3;

  @Column(name = "log_content")
  private String logContent;
}
