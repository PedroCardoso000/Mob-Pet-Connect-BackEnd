package com.petconnect.petconnect.controllers;

import com.petconnect.petconnect.Exceptions.PasswordMatchException;
import com.petconnect.petconnect.Exceptions.UserNotFoundException;
import com.petconnect.petconnect.Exceptions.UserUnauthorizedException;
import com.petconnect.petconnect.dtos.ErrorDto;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleUsernameNotFoundException(UserNotFoundException ex) {
        return new ErrorDto(ex.getMessage());
    }
}
