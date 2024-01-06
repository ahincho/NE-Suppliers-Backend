package com.unsa.suppliers.domain.exceptions.roles;

import static com.unsa.suppliers.domain.exceptions.roles.RoleExceptionMessages.IN_USE;

public class RoleInUseException extends Exception {
    public RoleInUseException() {
        super(IN_USE);
    }
}
