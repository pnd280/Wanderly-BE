package com.wanderly.wanderlybe.controllers;

import com.wanderly.wanderlybe.utils.AppUtils;
import com.wanderly.wanderlybe.exceptions.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(value = ApiException.class)
  public ResponseEntity<Object> handleApiException(ApiException e) {
    return ResponseEntity.status(e.getStatus()).body(AppUtils.ErrorResponse(e.getMessage()));
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
    Map<String, String> errorFields = new HashMap<>();
    e.getBindingResult().getFieldErrors().forEach(fieldError -> {
      errorFields.put(fieldError.getField(), fieldError.getDefaultMessage());
    });

    Map<String, Object> resp = AppUtils.ErrorResponse("Validation failed", errorFields);

    return ResponseEntity.badRequest().body(resp);
  }
}
