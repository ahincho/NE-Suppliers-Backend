package com.unsa.suppliers.infrastructure.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

@RestControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<NotValidField>> notValidEntityOrRequest(MethodArgumentNotValidException notValidException) {
        var errors = notValidException.getFieldErrors().stream().map(NotValidField::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }
}
