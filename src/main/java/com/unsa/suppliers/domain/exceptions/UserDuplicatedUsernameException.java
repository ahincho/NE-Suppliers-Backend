package com.unsa.suppliers.domain.exceptions;

import static com.unsa.suppliers.domain.exceptions.UserExceptionMessages.DUPLICATED_USERNAME;

public class UserDuplicatedUsernameException extends Exception {
    public UserDuplicatedUsernameException() {
        super(DUPLICATED_USERNAME);
    }
}
