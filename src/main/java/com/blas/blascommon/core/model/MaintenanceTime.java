package com.blas.blascommon.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
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
@Document(collection = "maintenance_times")
public class MaintenanceTime {

  @Id
  @NotEmpty
  private String id;

  @NotEmpty
  private String service;

  @NotEmpty
  @Field("start_time")
  private LocalDateTime startTime;

  @NotEmpty
  @Field("end_time")
  private LocalDateTime endTime;

  @NotEmpty
  @Field("notification_message")
  private String notificationMessage;
}
