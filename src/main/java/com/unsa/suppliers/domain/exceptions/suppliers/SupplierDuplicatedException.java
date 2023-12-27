package com.unsa.suppliers.domain.exceptions.suppliers;

import static com.unsa.suppliers.domain.exceptions.suppliers.SupplierExceptionMessages.DUPLICATED;

public class SupplierDuplicatedException extends Exception {
    public SupplierDuplicatedException() {
        super(DUPLICATED);
    }
}
