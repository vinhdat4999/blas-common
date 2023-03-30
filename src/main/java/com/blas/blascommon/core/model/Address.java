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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

  @Id
  @Column(name = "address_id", length = 50, nullable = false)
  @NotEmpty
  private String addressId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_addresses_1"))
  @NotNull
  private UserDetail userDetail;

  @Column(name = "city", length = 50, nullable = false)
  @NotEmpty
  private String city;

  @Column(name = "district", length = 50, nullable = false)
  @NotEmpty
  private String district;

  @Column(name = "ward", length = 50, nullable = false)
  @NotEmpty
  private String ward;

  @Column(name = "address_detail", length = 200, nullable = false)
  @NotEmpty
  private String addressDetail;

  @Column(name = "is_default")
  @NotNull
  private boolean isDefault;

  @Column(name = "is_active")
  @NotNull
  private boolean isActive;
}
