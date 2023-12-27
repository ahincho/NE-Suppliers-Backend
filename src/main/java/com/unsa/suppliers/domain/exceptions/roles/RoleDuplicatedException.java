package com.unsa.suppliers.domain.exceptions.roles;

import static com.unsa.suppliers.domain.exceptions.roles.RoleExceptionMessages.DUPLICATED;

public class RoleDuplicatedException extends Exception {
    public RoleDuplicatedException() {
        super(DUPLICATED);
    }
}
