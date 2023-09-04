package com.blas.blascommon.exceptions;

import static java.lang.System.currentTimeMillis;
import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.GATEWAY_TIMEOUT;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.blas.blascommon.exceptions.types.BadGatewayException;
import com.blas.blascommon.exceptions.types.BadRequestException;
import com.blas.blascommon.exceptions.types.BlasException;
import com.blas.blascommon.exceptions.types.ForbiddenException;
import com.blas.blascommon.exceptions.types.GatewayTimeoutException;
import com.blas.blascommon.exceptions.types.MaintenanceException;
import com.blas.blascommon.exceptions.types.NotAcceptableException;
import com.blas.blascommon.exceptions.types.NotFoundException;
import com.blas.blascommon.exceptions.types.PaymentException;
import com.blas.blascommon.exceptions.types.ServiceUnavailableException;
import com.blas.blascommon.exceptions.types.UnauthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(BadGatewayException exception) {
    ExceptionResponse error = new ExceptionResponse();
    error.setHttpCode(BAD_GATEWAY.value());
    error.setMessage(exception.getMessage());
    error.setBlasErrorCode(new BlasErrorCode(exception.getBlasErrorCodeEnum()));
    error.setTimeStamp(currentTimeMillis());
    return new ResponseEntity<>(error, BAD_GATEWAY);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(BadRequestException exception) {
    ExceptionResponse error = new ExceptionResponse();
    error.setHttpCode(BAD_REQUEST.value());
    error.setMessage(exception.getMessage());
    error.setBlasErrorCode(new BlasErrorCode(exception.getBlasErrorCodeEnum()));
    error.setTimeStamp(currentTimeMillis());
    return new ResponseEntity<>(error, BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(ForbiddenException exception) {
    ExceptionResponse error = new ExceptionResponse();
    error.setHttpCode(FORBIDDEN.value());
    error.setMessage(exception.getMessage());
    error.setBlasErrorCode(new BlasErrorCode(exception.getBlasErrorCodeEnum()));
    error.setTimeStamp(currentTimeMillis());
    return new ResponseEntity<>(error, FORBIDDEN);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(GatewayTimeoutException exception) {
    ExceptionResponse error = new ExceptionResponse();
    error.setHttpCode(GATEWAY_TIMEOUT.value());
    error.setMessage(exception.getMessage());
    error.setBlasErrorCode(new BlasErrorCode(exception.getBlasErrorCodeEnum()));
    error.setTimeStamp(currentTimeMillis());
    return new ResponseEntity<>(error, GATEWAY_TIMEOUT);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(NotFoundException exception) {
    ExceptionResponse error = new ExceptionResponse();
    error.setHttpCode(NOT_FOUND.value());
    error.setMessage(exception.getMessage());
    error.setBlasErrorCode(new BlasErrorCode(exception.getBlasErrorCodeEnum()));
    error.setTimeStamp(currentTimeMillis());
    return new ResponseEntity<>(error, NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(ServiceUnavailableException exception) {
    ExceptionResponse error = new ExceptionResponse();
    error.setHttpCode(SERVICE_UNAVAILABLE.value());
    error.setMessage(exception.getMessage());
    error.setBlasErrorCode(new BlasErrorCode(exception.getBlasErrorCodeEnum()));
    error.setTimeStamp(currentTimeMillis());
    return new ResponseEntity<>(error, SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(UnauthorizedException exception) {
    ExceptionResponse error = new ExceptionResponse();
    error.setHttpCode(UNAUTHORIZED.value());
    error.setMessage(exception.getMessage());
    error.setBlasErrorCode(new BlasErrorCode(exception.getBlasErrorCodeEnum()));
    error.setTimeStamp(currentTimeMillis());
    return new ResponseEntity<>(error, UNAUTHORIZED);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(BlasException exception) {
    ExceptionResponse error = new ExceptionResponse();
    error.setHttpCode(exception.getBlasErrorCodeEnum().getHttpCode());
    error.setMessage(exception.getMessage());
    error.setBlasErrorCode(new BlasErrorCode(exception.getBlasErrorCodeEnum()));
    error.setTimeStamp(currentTimeMillis());
    return new ResponseEntity<>(error, FORBIDDEN);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(PaymentException exception) {
    return getNotAcceptExceptionResponseResponseEntity(exception.getBlasErrorCodeEnum(),
        exception.getTransactionId(), exception.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(NotAcceptableException exception) {
    return getNotAcceptExceptionResponseResponseEntity(exception.getBlasErrorCodeEnum(),
        exception.getTransactionId(), exception.getMessage());
  }

  private ResponseEntity<ExceptionResponse> getNotAcceptExceptionResponseResponseEntity(
      BlasErrorCodeEnum blasErrorCodeEnum, String transactionId, String message) {
    PaymentExceptionResponse error = new PaymentExceptionResponse();
    error.setBlasErrorCode(new BlasErrorCode(blasErrorCodeEnum));
    error.setTransactionId(transactionId);
    error.setHttpCode(NOT_ACCEPTABLE.value());
    error.setMessage(message);
    error.setTimeStamp(currentTimeMillis());
    return new ResponseEntity<>(error, NOT_ACCEPTABLE);
  }

  @ExceptionHandler
  public ResponseEntity<MaintenanceExceptionResponse> handleException(
      MaintenanceException exception) {
    MaintenanceExceptionResponse error = new MaintenanceExceptionResponse();
    error.setBlasErrorCode(new BlasErrorCode(exception.getBlasErrorCodeEnum()));
    error.setHttpCode(SERVICE_UNAVAILABLE.value());
    error.setMessage(exception.getMessage());
    error.setMaintenanceTimeResponse(exception.getMaintenanceTimeResponse());
    error.setTimeStamp(currentTimeMillis());
    return new ResponseEntity<>(error, SERVICE_UNAVAILABLE);
  }
}
