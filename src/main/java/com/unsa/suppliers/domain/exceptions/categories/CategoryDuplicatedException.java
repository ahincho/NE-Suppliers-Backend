package com.unsa.suppliers.domain.exceptions.categories;

import static com.unsa.suppliers.domain.exceptions.categories.CategoryExceptionMessages.DUPLICATED;

public class CategoryDuplicatedException extends Exception {
    public CategoryDuplicatedException() {
        super(DUPLICATED);
    }
}
