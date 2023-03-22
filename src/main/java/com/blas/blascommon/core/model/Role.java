package com.blas.blascommon.core.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
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
public class Role implements Serializable {

  @Id
  @Column(name = "role_id", length = 50, nullable = false)
  @NotEmpty
  private String roleId;

  @Column(name = "role_name", length = 50, nullable = false)
  @NotEmpty
  private String roleName;

  @Column(name = "is_active")
  @NotEmpty
  private boolean isActive;
}
