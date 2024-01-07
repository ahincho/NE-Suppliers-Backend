package com.unsa.suppliers.application.mappers;

import com.unsa.suppliers.domain.dtos.states.StateRequest;
import com.unsa.suppliers.domain.dtos.states.StateResponse;
import com.unsa.suppliers.domain.entities.StateEntity;
import org.springframework.stereotype.Component;

@Component
public class StateMapper {
    public StateResponse entityToResponse(StateEntity stateEntity) {
        return StateResponse.builder()
                .id(stateEntity.getId())
                .name(stateEntity.getName())
                .build();
    }
    public StateEntity requestToEntity(StateRequest stateRequest) {
        return StateEntity.builder()
                .name(stateRequest.getName())
                .build();
    }
}
