package com.unsa.suppliers.infrastructure.controllers;

import com.unsa.suppliers.application.services.UserService;
import com.unsa.suppliers.domain.dtos.users.UserRequest;
import com.unsa.suppliers.domain.dtos.users.UserResponse;
import com.unsa.suppliers.domain.entities.UserEntity;
import com.unsa.suppliers.domain.exceptions.roles.RoleNotFoundException;
import com.unsa.suppliers.domain.exceptions.users.UserDuplicatedEmailException;
import com.unsa.suppliers.domain.exceptions.users.UserDuplicatedUsernameException;
import com.unsa.suppliers.domain.mappers.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest userRequest) throws UserDuplicatedEmailException, UserDuplicatedUsernameException, RoleNotFoundException {
        UserEntity savedUserEntity = userService.createUser(UserMapper.requestToEntity(userRequest));
        return ResponseEntity.ok(UserMapper.entityToResponse(savedUserEntity));
    }
}
