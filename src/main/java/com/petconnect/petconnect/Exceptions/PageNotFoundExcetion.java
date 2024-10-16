package com.petconnect.petconnect.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Path not found")
public class PageNotFoundExcetion extends RuntimeException {
    public PageNotFoundExcetion(String message) {
        super(message);
    }
}
