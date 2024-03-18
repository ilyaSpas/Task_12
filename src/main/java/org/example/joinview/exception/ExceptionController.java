package org.example.joinview.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserExceptionController {

    @ExceptionHandler
    private ResponseEntity<UserExceptionResponse> handleException(UserExceptionResponse e) {
        UserExceptionResponse response = new UserExceptionResponse("User not found!", LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserExceptionResponse> handleException(CustomUserException e) {
        UserExceptionResponse response = new UserExceptionResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
