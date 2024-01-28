package com.blas.blascommon.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BlasErrorCodeEnum {
  MSG_BLAS_APP_FAILURE(500, 1, "Blas Application Failure"),
  MSG_BLAS_APP_UNAVAILABLE(503, 2, "Blas Application Unavailable"),
  MSG_FAILURE(500, 3, "Failure"),
  MSG_TIMEOUT(504, 4, "Timeout"),
  MSG_SERVER_ERROR(500, 5, "Server Error"),
  MSG_FORMATTING_ERROR(400, 6, "Formatting Error"),
  MSG_CACHE_ERROR(503, 7, "Caching Error"),
  MSG_TRANSLATING_ERROR(503, 8, "Translation Error"),
  MSG_NOT_IMPLEMENTING_ERROR(501, 9, "Not Implementing Error"),
  MSG_THIRD_PARTY_ERROR(503, 10, "Third Party Error"),
  MSG_STATUS_DISABLED(503, 11, "Status Disabled"),
  MSG_STATUS_INACTIVE(503, 12, "Status Inactive"),
  MSG_INVALID_ID(400, 13, "Invalid ID"),
  MSG_INVALID_USER_ID(400, 14, "Invalid User ID"),
  MSG_INVALID_PASSWORD(400, 15, "Invalid Password"),
  MSG_EXPIRED_PASSWORD(400, 16, "Expired Password"),
  MSG_INVALID_EMAIL(400, 17, "Invalid Email Address"),
  MSG_INVALID_PHONE_NUM(400, 18, "Invalid Phone Number"),
  MSG_INVALID_FORMAT(400, 19, "Invalid Format"),
  MSG_INVALID_INPUT(400, 20, "Invalid Input"),
  MSG_USER_NOT_AUTHORIZED(401, 21, "User Not Authorized"),
  MSG_FAIL_TO_ESTABLISH_SESSION(401, 22, "Fail To Establish Session"),
  MSG_FAIL_TO_LOGIN(401, 23, "Fail To Login"),
  MSG_LOGIN_TIMEOUT(408, 24, "Login Timeout"),
  MSG_ACCOUNT_BLOCKED(403, 25, "Account Blocked"),
  MSG_ACCOUNT_INACTIVE(403, 26, "Account Inactive"),
  MSG_UNKNOWN_ERROR(500, 27, "Unknown Error"),
  MSG_IN_MAINTENANCE(503, 28, "Service is in maintenance"),
  MSG_SECURITY_ERROR(403, 29, "Security Error");

  private final int httpCode;
  private final int errorCode;
  private final String messageDescription;

}
