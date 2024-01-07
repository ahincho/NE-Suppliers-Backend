package com.unsa.suppliers.domain.exceptions.states;

import static com.unsa.suppliers.domain.exceptions.states.StateExceptionMessages.IN_USE;

public class StateInUseException extends Exception {
    public StateInUseException() {
        super(IN_USE);
    }
}
