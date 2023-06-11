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
public class MaintenanceTimeResponse {

  private boolean isInMaintenance;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private String notificationMessage;
}
