package com.unsa.suppliers.domain.exceptions.users;

import static com.unsa.suppliers.domain.exceptions.users.UserExceptionMessages.NOT_FOUND;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super(NOT_FOUND);
    }
}
