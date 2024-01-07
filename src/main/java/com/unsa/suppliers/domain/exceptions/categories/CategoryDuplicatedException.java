package com.unsa.suppliers.domain.exceptions.categories;

import com.unsa.suppliers.domain.entities.CategoryEntity;

import static com.unsa.suppliers.domain.exceptions.categories.CategoryExceptionMessages.DUPLICATED;

public class CategoryDuplicatedException extends Exception {
    public CategoryDuplicatedException() {
        super(DUPLICATED);
    }
}
