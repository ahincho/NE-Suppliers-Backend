package com.unsa.suppliers.domain.exceptions.states;

import static com.unsa.suppliers.domain.exceptions.states.StateExceptionMessages.DUPLICATED;

public class StateDuplicatedException extends Exception {
    public StateDuplicatedException() {
        super(DUPLICATED);
    }
}
