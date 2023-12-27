package com.unsa.suppliers.domain.exceptions.suppliers;

import static com.unsa.suppliers.domain.exceptions.suppliers.SupplierExceptionMessages.NOT_FOUND;

public class SupplierNotFoundException extends Exception {
    public SupplierNotFoundException() {
        super(NOT_FOUND);
    }
}
