package com.blas.blascommon.core.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
  @NotEmpty
  private LocalDateTime timeGenerate;

  @Column(name = "time_used")
  private LocalDateTime timeUsed;
}
