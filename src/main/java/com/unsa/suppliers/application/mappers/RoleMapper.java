package com.unsa.suppliers.application.mappers;

import com.unsa.suppliers.domain.dtos.roles.RoleRequest;
import com.unsa.suppliers.domain.dtos.roles.RoleResponse;
import com.unsa.suppliers.domain.entities.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponse entityToResponse(RoleEntity roleEntity) {
        return RoleResponse.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .build();
    }
    public RoleEntity requestToEntity(RoleRequest roleRequest) {
        return RoleEntity.builder()
                .name(roleRequest.getName())
                .build();
    }
}
