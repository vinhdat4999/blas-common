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
@Table(name = "blas_centralized_configurations")
public class BlasConfiguration {

  @Id
  @Column(name = "config_id", length = 50, nullable = false)
  @NotEmpty
  private String configId;

  @Column(name = "config_main_type", length = 20)
  private String configMainType;

  @Column(name = "config_major_type", length = 20)
  private String configMajorType;

  @Column(name = "config_minor_type", length = 20)
  private String configMinorType;

  @Column(name = "config_label_1", length = 20)
  private String configLabel1;

  @Column(name = "config_label_2", length = 20)
  private String configLabel2;

  @Column(name = "config_label_3", length = 20)
  private String configLabel3;

  @Column(name = "config_label_4", length = 20)
  private String configLabel4;

  @Column(name = "config_label_5", length = 20)
  private String configLabel5;

  @Column(name = "config_key", length = 50, nullable = false)
  @NotEmpty
  private String configKey;

  @Column(name = "config_value", length = 200)
  private String configValue;

  @Column(name = "config_desc1", length = 50)
  private String configDesc1;

  @Column(name = "config_desc2", length = 50)
  private String configDesc2;

  @Column(name = "config_desc3", length = 50)
  private String configDesc3;

  @Column(name = "create_time", nullable = false)
  @NotEmpty
  private LocalDateTime createTime;

  @Column(name = "last_update", nullable = false)
  @NotEmpty
  private LocalDateTime lastUpdate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "create_by", foreignKey = @ForeignKey(name = "fk_blas_centralized_configuration_1"))
  @NotNull
  private AuthUser createBy;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "update_by", foreignKey = @ForeignKey(name = "fk_blas_centralized_configuration_2"))
  @NotNull
  private AuthUser updateBy;

  @Column(name = "is_active")
  @NotNull
  private boolean isActive;
}
