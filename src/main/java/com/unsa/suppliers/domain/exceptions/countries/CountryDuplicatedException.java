package com.unsa.suppliers.domain.exceptions.countries;

import static com.unsa.suppliers.domain.exceptions.countries.CountryExceptionMessages.DUPLICATED;

public class CountryDuplicatedException extends Exception {
    public CountryDuplicatedException() {
        super(DUPLICATED);
    }
}
