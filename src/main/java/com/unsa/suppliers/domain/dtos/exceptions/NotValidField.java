package com.unsa.suppliers.domain.dtos.exceptions;

import org.springframework.validation.FieldError;

public record NotValidField(String field, String message) {
    public NotValidField(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
