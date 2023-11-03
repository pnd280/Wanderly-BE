package com.wanderly.wanderlybe.exceptions;

import org.springframework.http.HttpStatus;


public class ApiException extends RuntimeException {

  private HttpStatus status;
  private String message;

  public ApiException(String message, HttpStatus status) {
    this.status = status;
    this.message = message;
  }

  public ApiException(String message) {
    this.status = HttpStatus.BAD_REQUEST;
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
