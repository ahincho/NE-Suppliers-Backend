package com.unsa.suppliers.application.services;

import com.unsa.suppliers.domain.entities.StateEntity;
import com.unsa.suppliers.domain.exceptions.states.*;
import com.unsa.suppliers.domain.repositories.StateRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StateService {
    public static final String ACTIVE_STATE = "Active";
    public static final String DISABLED_STATE = "Disabled";
    public static final String DELETED_STATE = "Deleted";
    private final StateRepository stateRepository;
    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }
    public List<StateEntity> getAllStates() {
        return stateRepository.findAll();
    }
    public StateEntity getStateById(Integer id) throws StateNotFoundException {
        return stateRepository.findById(id).orElseThrow(StateNotFoundException::new);
    }
    public StateEntity createState(StateEntity stateEntity) throws StateDuplicatedException {
        Optional<StateEntity> optionalState = stateRepository.findByName(stateEntity.getName());
        if (optionalState.isPresent()) { throw new StateDuplicatedException(); }
        return stateRepository.save(stateEntity);
    }
    public void updateState(Integer id, StateEntity stateEntity) throws StateNotFoundException, StateDuplicatedException {
        StateEntity optionalState = stateRepository.findById(id).orElseThrow(StateNotFoundException::new);
        if (!optionalState.getName().equals(stateEntity.getName())) {
            Optional<StateEntity> optionalStateName = stateRepository.findByName(stateEntity.getName());
            if (optionalStateName.isPresent()) { throw new StateDuplicatedException(); }
        }
        stateEntity.setId(id);
        this.stateRepository.save(stateEntity);
    }
    public void deleteState(Integer id) throws StateNotFoundException, StateInUseException {
        StateEntity stateEntity = stateRepository.findById(id).orElseThrow(StateNotFoundException::new);
        switch (stateEntity.getName()) {
            case ACTIVE_STATE, DISABLED_STATE, DELETED_STATE -> throw new StateInUseException();
            default -> stateRepository.deleteById(id);
        }
    }
}
