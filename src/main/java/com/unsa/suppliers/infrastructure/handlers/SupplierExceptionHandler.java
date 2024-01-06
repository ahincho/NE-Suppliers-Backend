package com.unsa.suppliers.infrastructure.handlers;

import com.unsa.suppliers.domain.exceptions.suppliers.SupplierDuplicatedNameException;
import com.unsa.suppliers.domain.exceptions.suppliers.SupplierDuplicatedRucException;
import com.unsa.suppliers.domain.exceptions.suppliers.SupplierNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SupplierExceptionHandler {
    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<Void> supplierNotFound() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler({SupplierDuplicatedNameException.class, SupplierDuplicatedRucException.class})
    public ResponseEntity<Void> supplierDuplicated() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
