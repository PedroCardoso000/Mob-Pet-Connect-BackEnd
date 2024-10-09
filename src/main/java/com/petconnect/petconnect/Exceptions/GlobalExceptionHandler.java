//package com.petconnect.petconnect.Exceptions;
/**

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

 *
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
    String body = "User not found: " + ex.getMessage();
    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserUnauthorizedException.class)
  public ResponseEntity<Object> handleUserUnauthorizedException(UserUnauthorizedException ex, WebRequest request) {
    String body = "Unauthorized access: " + ex.getMessage();
    return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
  }
}
 */

