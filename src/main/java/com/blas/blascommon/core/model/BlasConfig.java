package com.blas.blascommon.core.model;

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
@Table(name = "blas_configs")
public class BlasConfig {

  @Id
  @Column(name = "key", length = 300, nullable = false)
  @NotEmpty
  private String key;

  @Column(name = "value")
  private String value;
}
