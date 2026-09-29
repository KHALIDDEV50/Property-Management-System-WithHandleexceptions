package com.example.propertymanagementsystem.Advice;

import com.example.propertymanagementsystem.APIResponse.ApiException;
import com.example.propertymanagementsystem.APIResponse.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ControllerAdvice {

    // Handle our custom exceptions
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e) {

        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage()));
    }

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(
            MethodArgumentNotValidException e) {

        String message = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        return ResponseEntity.status(400).body(new ApiResponse(message));
    }

    // Handle unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {

        return ResponseEntity.status(500).body(new ApiResponse("Something went wrong"));
    }

    // Handle wrong data type in PathVariable
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatch(MethodArgumentTypeMismatchException e) {

        return ResponseEntity.status(400).body(new ApiResponse("ID must be a number"));
    }
}