package com.unsa.suppliers.domain.exceptions.roles;

import static com.unsa.suppliers.domain.exceptions.roles.RoleExceptionMessages.NOT_FOUND;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException() {
        super(NOT_FOUND);
    }
}
