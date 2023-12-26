package com.unsa.suppliers.domain.exceptions;

import static com.unsa.suppliers.domain.exceptions.UserExceptionMessages.NOT_FOUND;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super(NOT_FOUND);
    }
}
