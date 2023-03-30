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
@Table(name = "notifications")
public class Notification {

  @Id
  @Column(name = "nofitication_id", length = 50, nullable = false)
  @NotEmpty
  private String nofiticationId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_notifications_1"))
  @NotNull
  private UserDetail userDetail;

  @Column(name = "content", length = 200, nullable = false)
  @NotEmpty
  private String content;

  @Column(name = "time")
  @NotNull
  private LocalDateTime time;

  @Column(name = "link_ref", length = 200)
  private String linkRef;

  @Column(name = "is_read")
  @NotNull
  private boolean isRead;
}
