package com.blas.blascommon.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details")
public class UserDetail implements Serializable {

  @Id
  @Column(name = "user_id", length = 50, nullable = false)
  @NotEmpty
  private String userId;

  @OneToOne
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_details_1"))
  @JsonBackReference
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private AuthUser authUser;

  @Column(name = "first_name", length = 35, nullable = false)
  @NotEmpty
  private String firstName;

  @Column(name = "last_name", length = 20)
  private String lastName;

  @Column(name = "phone_number", length = 15)
  private String phoneNumber;

  @Column(name = "email", length = 60, nullable = false)
  @NotEmpty
  @Email
  private String email;

  @Column(name = "telegram_chat_id", length = 15)
  private String telegramChatId;

  @Column(name = "gender")
  private boolean gender;

  @Column(name = "birthday")
  private LocalDate birthday;

  @Column(name = "avatar_path", length = 200)
  private String avatarPath;

  @Column(name = "b_coin")
  @NotNull
  private int bCoin;
}
