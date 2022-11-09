package com.blas.blascommon.exceptions;

import com.blas.blascommon.exceptions.types.BadGatewayException;
import com.blas.blascommon.exceptions.types.BadRequestException;
import com.blas.blascommon.exceptions.types.ForbiddenException;
import com.blas.blascommon.exceptions.types.GatewayTimeoutException;
import com.blas.blascommon.exceptions.types.NotFoundException;
import com.blas.blascommon.exceptions.types.ServiceUnavailableException;
import com.blas.blascommon.exceptions.types.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(BadGatewayException exc) {

    ExceptionResponse error = new ExceptionResponse();

    error.setStatus(HttpStatus.BAD_GATEWAY.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(BadRequestException exc) {

    ExceptionResponse error = new ExceptionResponse();

    error.setStatus(HttpStatus.BAD_REQUEST.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(ForbiddenException exc) {

    ExceptionResponse error = new ExceptionResponse();

    error.setStatus(HttpStatus.FORBIDDEN.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(GatewayTimeoutException exc) {

    ExceptionResponse error = new ExceptionResponse();

    error.setStatus(HttpStatus.GATEWAY_TIMEOUT.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, HttpStatus.GATEWAY_TIMEOUT);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(NotFoundException exc) {

    ExceptionResponse error = new ExceptionResponse();

    error.setStatus(HttpStatus.NOT_FOUND.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(ServiceUnavailableException exc) {

    ExceptionResponse error = new ExceptionResponse();

    error.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler
  public ResponseEntity<ExceptionResponse> handleException(UnauthorizedException exc) {

    ExceptionResponse error = new ExceptionResponse();

    error.setStatus(HttpStatus.UNAUTHORIZED.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
  }
}
