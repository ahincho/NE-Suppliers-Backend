package com.unsa.suppliers.application.mappers;

import com.unsa.suppliers.domain.dtos.roles.RoleRequest;
import com.unsa.suppliers.domain.dtos.roles.RoleResponse;
import com.unsa.suppliers.domain.entities.RoleEntity;

public class RoleMapper {
    public static RoleResponse entityToResponse(RoleEntity roleEntity) {
        return RoleResponse.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .build();
    }
    public static RoleEntity requestToEntity(RoleRequest roleRequest) {
        return RoleEntity.builder()
                .name(roleRequest.getName())
                .build();
    }
}
