package com.petconnect.petconnect.controllers;

import com.petconnect.petconnect.Exceptions.PasswordMatchException;
import com.petconnect.petconnect.Exceptions.UserNotFoundException;
import com.petconnect.petconnect.Exceptions.UserUnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(PasswordMatchException.class)
    public ResponseEntity<String> handlePasswordMatchException(PasswordMatchException e, WebRequest request) {
        String body = "User not found: " + e.getMessage();
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(UserUnauthorizedException.class)
    public ResponseEntity<String> handleUserUnauthorizedException(UserUnauthorizedException e, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
