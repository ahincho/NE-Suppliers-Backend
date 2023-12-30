package com.unsa.suppliers.application.mappers;

import com.unsa.suppliers.domain.dtos.users.UserRequest;
import com.unsa.suppliers.domain.dtos.users.UserResponse;
import com.unsa.suppliers.domain.dtos.users.UserUpdateRequest;
import com.unsa.suppliers.domain.entities.UserEntity;

public class UserMapper {
    public static UserResponse entityToResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .lastname(userEntity.getLastname())
                .username(userEntity.getUsername())
                .build();
    }
    public static UserEntity requestToEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .name(userRequest.getName())
                .lastname(userRequest.getLastname())
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }
    public static UserEntity updateRequestToEntity(UserUpdateRequest userUpdateRequest) {
        return UserEntity.builder()
                .name(userUpdateRequest.getName())
                .lastname(userUpdateRequest.getLastname())
                .username(userUpdateRequest.getUsername())
                .build();
    }
}
