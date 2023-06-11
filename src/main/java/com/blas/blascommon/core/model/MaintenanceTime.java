package com.blas.blascommon.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "maintenance_times")
public class MaintenanceTime {

  @Id
  @Column(name = "maintenance_time_id", length = 50, nullable = false)
  @NotEmpty
  private String maintenanceTimeId;

  @Column(name = "service", length = 100, nullable = false)
  @NotEmpty
  private String service;

  @Column(name = "start_time", nullable = false)
  @NotEmpty
  private LocalDateTime startTime;

  @Column(name = "end_time", nullable = false)
  @NotEmpty
  private LocalDateTime endTime;

  @Column(name = "notification_message", length = 200, nullable = false)
  @NotEmpty
  private String notificationMessage;
}
