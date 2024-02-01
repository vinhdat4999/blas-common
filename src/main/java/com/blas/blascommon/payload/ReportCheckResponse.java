package com.blas.blascommon.payload;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportCheckResponse {

  private String reportCode;
  private String reportType;
  private LocalDateTime logTime;
  private String username;
  private String message;
}
