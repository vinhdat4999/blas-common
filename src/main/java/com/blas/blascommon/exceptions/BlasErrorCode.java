package com.blas.blascommon.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BlasErrorCode {
  MSG_BLAS_APP_FAILURE(500, 30, "Blas Application Failure"),
  MSG_BLAS_APP_UNAVAILABLE(503, 31, "Blas Application Unavailable"),
  MSG_FAILURE(503, 31, "Failure"),
  MSG_TIMEOUT(503, 31, "Timeout"),
  MSG_SERVER_ERROR(503, 31, "Server Error"),
  MSG_FORMATTING_ERROR(503, 31, "Formatting Error"),
  MSG_CACHE_ERROR(503, 31, "Caching Error"),
  MSG_TRANSLATING_ERROR(503, 31, "Translation Error"),
  MSG_NOT_IMPLEMENTING_ERROR(503, 31, "Not Implementing Error"),
  MSG_THIRD_PARTY_ERROR(503, 31, "Third Party Error"),
  MSG_STATUS_DISABLED(503, 31, "Status Disabled"),
  MSG_STATUS_INACTIVE(503, 31, "Status Inactive"),
  MSG_INVALID_ID(503, 31, "Invalid ID"),
  MSG_INVALID_USER_ID(503, 31, "Invalid User ID"),
  MSG_INVALID_PASSWORD(503, 31, "Invalid Password"),
  MSG_EXPIRED_PASSWORD(503, 31, "Expired Password"),
  MSG_INVALID_EMAIL(503, 31, "Invalid Email Address"),
  MSG_INVALID_PHONE_NUM(503, 31, "Invalid Phone Number"),
  MSG_INVALID_FORMAT(503, 31, "Invalid Format"),
  MSG_INVALID_INPUT(503, 31, "Invalid Input"),
  MSG_USER_NOT_AUTHORIZED(503, 31, "User Not Authorized"),
  MSG_FAIL_TO_ESTABLISH_SESSION(503, 31, "Fail To Establish Session"),
  MSG_FAIL_TO_LOGIN(503, 31, "Fail To Login"),
  MSG_LOGIN_TIMEOUT(503, 31, "Login Timeout"),
  MSG_ACCOUNT_BLOCKED(503, 31, "Account Blocked"),
  MSG_ACCOUNT_INACTIVE(503, 31, "Account Inactive"),
  MSG_UNKNOWN_ERROR(503, 31, "Unknown Error"),
  MSG_SECURITY_ERROR(503, 31, "Security Error");


  private final int httpCode;
  private final int blasErrorCode;
  private final String messageDescription;

}
