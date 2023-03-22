package com.blas.blascommon.core.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
  @NotEmpty
  private int countLoginFailed;

  @Column(name = "is_block")
  @NotEmpty
  private boolean isBlock;

  @Column(name = "is_active")
  @NotEmpty
  private boolean isActive;

  @Column(name = "provider", length = 20, nullable = false)
  @NotEmpty
  private String provider;
}
