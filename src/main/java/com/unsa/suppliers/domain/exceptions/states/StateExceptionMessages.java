package com.unsa.suppliers.domain.exceptions.states;

public class StateExceptionMessages {
    public static final String DUPLICATED = "This state is already register in the database";
    public static final String NOT_FOUND = "There is no state with the requested identifier";
    public static final String IN_USE = "This state is used on other tables";
}
