package org.example.joinview.exception;

import org.example.joinview.exception.order.OrderNotFoundException;
import org.example.joinview.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    private ResponseEntity<CustomExceptionResponse> handleException(UserNotFoundException e) {
        CustomExceptionResponse response = new CustomExceptionResponse("User not founded!", LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<CustomExceptionResponse> handleException(OrderNotFoundException e) {
        CustomExceptionResponse response = new CustomExceptionResponse("Ordered not founded!", LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<CustomExceptionResponse> handleException(CustomException e) {
        CustomExceptionResponse response = new CustomExceptionResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<CustomExceptionResponse> handleException(Exception e) {
        CustomExceptionResponse response = new CustomExceptionResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
