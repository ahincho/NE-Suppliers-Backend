package com.unsa.suppliers.domain.exceptions.users;

import static com.unsa.suppliers.domain.exceptions.users.UserExceptionMessages.DUPLICATED_EMAIL;

public class UserDuplicatedEmailException extends Exception {
    public UserDuplicatedEmailException() {
        super(DUPLICATED_EMAIL);
    }
}
