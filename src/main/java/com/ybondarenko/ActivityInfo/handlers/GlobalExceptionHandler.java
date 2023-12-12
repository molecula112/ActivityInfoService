package com.ybondarenko.ActivityInfo.handlers;

import com.ybondarenko.ActivityInfo.exceptions.ActivityNotFoundException;
import com.ybondarenko.ActivityInfo.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException ex) {
        return "error-page";
    }

    @ExceptionHandler(ActivityNotFoundException.class)
    public String handleActivityNotFoundException(ActivityNotFoundException ex) {
        return "error-page";
    }

}
