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

    public UserDetail() {
    }

    public UserDetail(String userId, AuthUser authUser, String firstName, String lastName,
            String phoneNumber, String email, boolean gender, LocalDate birthday, String avatarPath,
            int bCoin) {
        this.userId = userId;
        this.authUser = authUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.avatarPath = avatarPath;
        this.bCoin = bCoin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public int getbCoin() {
        return bCoin;
    }

    public void setbCoin(int bCoin) {
        this.bCoin = bCoin;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "userId='" + userId + '\'' +
                ", authUser=" + authUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", avatarPath='" + avatarPath + '\'' +
                ", bCoin=" + bCoin +
                '}';
    }
}
