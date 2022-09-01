package com.blas.blascommon.core.model;

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
import lombok.Data;

@Data
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

  @Column(name = "address", length = 200, nullable = false)
  @NotEmpty
  private String address;

  @Column(name = "is_default")
  @NotEmpty
  private boolean isDefault;

  @Column(name = "is_active")
  @NotEmpty
  private boolean isActive;
}