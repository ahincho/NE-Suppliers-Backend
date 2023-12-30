package com.unsa.suppliers.domain.exceptions.countries;

import static com.unsa.suppliers.domain.exceptions.countries.CountryExceptionMessages.NOT_FOUND;

public class CountryNotFoundException extends Exception {
    public CountryNotFoundException() {
        super(NOT_FOUND);
    }
}
