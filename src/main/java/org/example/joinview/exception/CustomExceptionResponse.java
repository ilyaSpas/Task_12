package org.example.joinview.exception.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
public class UserExceptionResponse {

    private String message;

    private LocalDateTime localDateTime;
}
