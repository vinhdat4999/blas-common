package com.blas.blascommon.core.model;

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
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details")
public class UserDetail {

  @Id
  @Column(name = "user_id", length = 50, nullable = false)
  @NotEmpty
  private String userId;

  @OneToOne
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_details_1"))
  private AuthUser authUser;

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

  @Column(name = "telegram_chat_id", length = 15)
  private String telegramChatId;

  @Column(name = "gender")
  @NotNull
  private boolean gender;

  @Column(name = "birthday", nullable = false)
  @NotNull
  private LocalDate birthday;

  @Column(name = "avatar_path", length = 200)
  private String avatarPath;

  @Column(name = "b_coin")
  @NotNull
  private int bCoin;
}
