package com.unsa.suppliers.domain.exceptions.users;

import static com.unsa.suppliers.domain.exceptions.users.UserExceptionMessages.DUPLICATED_USERNAME;

public class UserDuplicatedUsernameException extends Exception {
    public UserDuplicatedUsernameException() {
        super(DUPLICATED_USERNAME);
    }
}
