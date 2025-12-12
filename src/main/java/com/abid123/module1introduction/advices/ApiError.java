package com.abid123.module1introduction.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiError {
    HttpStatus status;
    String message;
}
