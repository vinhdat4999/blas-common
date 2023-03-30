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
@Table(name = "authen_keys")
public class AuthenKey {

  @Id
  @Column(name = "authen_id", length = 50, nullable = false)
  @NotEmpty
  private String authenId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_authen_keys_1"))
  @NotNull
  private AuthUser authUser;

  @Column(name = "`key`", length = 256, nullable = false)
  @NotEmpty
  private String key;

  @Column(name = "is_used")
  private boolean isUsed;

  @Column(name = "time_generate")
  @NotNull
  private LocalDateTime timeGenerate;

  @Column(name = "time_used")
  private LocalDateTime timeUsed;
}
