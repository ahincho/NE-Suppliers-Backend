package com.unsa.suppliers.domain.exceptions.states;

import static com.unsa.suppliers.domain.exceptions.states.StateExceptionMessages.NOT_FOUND;

public class StateNotFoundException extends Exception {
    public StateNotFoundException() {
        super(NOT_FOUND);
    }
}
