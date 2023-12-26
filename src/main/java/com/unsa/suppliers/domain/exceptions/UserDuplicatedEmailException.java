package com.unsa.suppliers.domain.exceptions;

import static com.unsa.suppliers.domain.exceptions.UserExceptionMessages.DUPLICATED_EMAIL;

public class UserDuplicatedEmailException extends Exception {
    public UserDuplicatedEmailException() {
        super(DUPLICATED_EMAIL);
    }
}
