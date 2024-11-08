package com.blas.blascommon.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
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
@Document(collection = "notifications")
public class Notification {

  @Id
  @NotEmpty
  @Field(name = "notification_id")
  private String notificationId;

  @Field(name = "creator_username")
  private String creatorUsername;

  @NotEmpty
  @Field(name = "receiver_username")
  private String receiverUsername;

  @NotEmpty
  @Field(name = "message")
  private String message;

  @Field(name = "is_read")
  @JsonProperty("isRead")
  private boolean isRead;

  @NotNull
  @Column(name = "created_time")
  private LocalDateTime createdTime;

  @Column(name = "read_time")
  private LocalDateTime readTime;

  @Column(name = "link_to")
  private String linkTo;
}
