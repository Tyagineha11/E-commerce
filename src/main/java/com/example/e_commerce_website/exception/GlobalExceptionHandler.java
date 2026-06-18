package com.example.e_commerce_website.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.e_commerce_website.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadTokenException.class)
    public ApiResponse handleBadTokenException(
            BadTokenException ex) {

        return new ApiResponse(
        		HttpStatus.UNAUTHORIZED.value(), 
        		ex.getMessage(), 
        		null 
        		);
         
        
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors =
                new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {

                    errors.put(
                            error.getField(),
                            error.getDefaultMessage());
                });

        return new ApiResponse(
                400,
                "Validation Failed",
                errors);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(
            Exception ex) {

        return new ApiResponse(
                500,
                ex.getMessage(),
                null);
    }
    
    @ExceptionHandler(CustomException.class)
    public ApiResponse handleCustomException(
            CustomException ex) {

        return new ApiResponse(
                ex.getCode(),
                ex.getMessage(),
                null
        );
    }
}