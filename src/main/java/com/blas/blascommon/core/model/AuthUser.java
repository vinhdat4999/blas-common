package com.blas.blascommon.core.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auth_users")
public class AuthUser implements Serializable {

  @Id
  @Column(name = "user_id", length = 50, nullable = false)
  @NotEmpty
  private String userId;

  @Column(name = "username", length = 20, nullable = false)
  @NotEmpty
  private String username;

  @OneToOne(mappedBy = "authUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @NotNull
  private UserDetail userDetail;

  @Column(name = "password", length = 256, nullable = false)
  @NotEmpty
  private String password;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_auth_users_1"))
  @NotNull
  private Role role;

  @Column(name = "count_login_failed")
  @NotNull
  private int countLoginFailed;

  @Column(name = "is_block")
  @NotNull
  private boolean isBlock;

  @Column(name = "is_active")
  @NotNull
  private boolean isActive;

  @Column(name = "provider", length = 20, nullable = false)
  @NotEmpty
  private String provider;
}
