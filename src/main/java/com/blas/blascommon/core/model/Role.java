package com.blas.blascommon.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

  @Id
  @Column(name = "role_id", length = 50, nullable = false)
  @NotEmpty
  private String roleId;

  @Column(name = "role_name", length = 50, nullable = false)
  @NotEmpty
  private String roleName;

  @Column(name = "is_active")
  @NotNull
  private boolean isActive;
}
