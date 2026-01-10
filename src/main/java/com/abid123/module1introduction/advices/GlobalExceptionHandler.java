package com.abid123.module1introduction.advices;

import com.abid123.module1introduction.Exceptions.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.core.AuthenticationException;

import java.security.PublicKey;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleResourceNotFoundException(Exception e){
        ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND ).message(e.getMessage()).build();
//        return new ResponseEntity<>(apiError, apiError.getStatus());  // -> constructor method
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError); -> builder method
        return buildResponseEntity(apiError);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<ApiError>> handleInternalServerError(Exception e){
////        return new ResponseEntity<>(
////                ApiError
////                        .builder()
////                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
////                        .message(e.getMessage()).build(),
////                HttpStatus.INTERNAL_SERVER_ERROR
////        );
//        ApiError  apiError = ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message(e.getMessage()).build();
//        return buildResponseEntity(apiError);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<String> message = e.getBindingResult().getFieldErrors().stream().map(error->error.getField() + " : " + error.getDefaultMessage()).collect(Collectors.toList());
        ApiError apiError = ApiError.builder().status(HttpStatus.INTERNAL_SERVER_ERROR).message("Input Validation failed").subErrors(message).build();
//        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleJwtException(JwtException e){
        ApiError apiError = ApiError.builder().status(HttpStatus.UNAUTHORIZED).message(e.getMessage()).build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleUnauthorizedException(Exception e){
        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message(e.getMessage())
                .build();
        return buildResponseEntity(apiError);
    }

    public ResponseEntity<ApiResponse<ApiError>> buildResponseEntity(ApiError apiError){
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
    }

}