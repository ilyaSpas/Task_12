package org.example.joinview.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class CustomExceptionResponse {

    private String message;

    private LocalDateTime localDateTime;
}
