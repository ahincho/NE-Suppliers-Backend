package com.unsa.suppliers.domain.exceptions.suppliers;

import static com.unsa.suppliers.domain.exceptions.suppliers.SupplierExceptionMessages.DUPLICATED_NAME;

public class SupplierDuplicatedNameException extends Exception {
    public SupplierDuplicatedNameException() {
        super(DUPLICATED_NAME);
    }
}
