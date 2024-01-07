package com.unsa.suppliers.infrastructure.handlers;

import com.unsa.suppliers.domain.exceptions.states.StateDuplicatedException;
import com.unsa.suppliers.domain.exceptions.states.StateInUseException;
import com.unsa.suppliers.domain.exceptions.states.StateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StateExceptionHandler {
    @ExceptionHandler(StateNotFoundException.class)
    public ResponseEntity<Void> stateNotFound() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler({StateDuplicatedException.class, StateInUseException.class})
    public ResponseEntity<Void> stateDuplicated() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
