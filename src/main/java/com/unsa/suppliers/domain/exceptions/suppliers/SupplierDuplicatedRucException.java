package com.unsa.suppliers.domain.exceptions.suppliers;

import static com.unsa.suppliers.domain.exceptions.suppliers.SupplierExceptionMessages.DUPLICATED_RUC;

public class SupplierDuplicatedRucException extends Exception {
    public SupplierDuplicatedRucException() {
        super(DUPLICATED_RUC);
    }
}
