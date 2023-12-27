package com.unsa.suppliers.infrastructure.handlers;

import com.unsa.suppliers.domain.exceptions.users.UserDuplicatedEmailException;
import com.unsa.suppliers.domain.exceptions.users.UserDuplicatedUsernameException;
import com.unsa.suppliers.domain.exceptions.users.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Void> userNotFound() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(UserDuplicatedEmailException.class)
    public ResponseEntity<Void> userDuplicatedEmail() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    @ExceptionHandler(UserDuplicatedUsernameException.class)
    public ResponseEntity<Void> userDuplicatedUsername() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Void> usernameNotFound() {
        return ResponseEntity.notFound().build();
    }
}
