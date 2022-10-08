package com.blas.blascommon.core.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "user_details")
public class UserDetail implements Serializable {

  @Id
  @Column(name = "user_id", length = 50, nullable = false)
  @NotEmpty
  private String userId;

  @OneToOne
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_details_1"))
  @NotNull
  private transient AuthUser authUser;

  @Column(name = "first_name", length = 35, nullable = false)
  @NotEmpty
  private String firstName;

  @Column(name = "last_name", length = 20, nullable = false)
  @NotEmpty
  private String lastName;

  @Column(name = "phone_number", length = 15, nullable = false)
  @NotEmpty
  private String phoneNumber;

  @Column(name = "email", length = 60, nullable = false)
  @NotEmpty
  @Email
  private String email;

  @Column(name = "gender")
  @NotEmpty
  private boolean gender;

  @Column(name = "birthday", nullable = false)
  @NotEmpty
  private LocalDate birthday;

  @Column(name = "avatar_path", length = 200)
  private String avatarPath;

  @Column(name = "b_coin")
  @NotEmpty
  private int bCoin;
}