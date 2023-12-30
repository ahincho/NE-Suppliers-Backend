package com.unsa.suppliers.domain.exceptions.categories;

import static com.unsa.suppliers.domain.exceptions.categories.CategoryExceptionMessages.NOT_FOUND;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException() {
        super(NOT_FOUND);
    }
}
