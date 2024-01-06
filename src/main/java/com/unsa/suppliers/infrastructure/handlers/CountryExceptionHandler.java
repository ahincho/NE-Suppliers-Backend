package com.unsa.suppliers.infrastructure.handlers;

import com.unsa.suppliers.domain.exceptions.countries.CountryDuplicatedException;
import com.unsa.suppliers.domain.exceptions.countries.CountryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CountryExceptionHandler {
    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<Void> countryNotFound() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(CountryDuplicatedException.class)
    public ResponseEntity<Void> countryDuplicated() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
