package com.unsa.suppliers.infrastructure.controllers;

import com.unsa.suppliers.application.services.UserService;
import com.unsa.suppliers.domain.dtos.users.UserRequest;
import com.unsa.suppliers.domain.dtos.users.UserResponse;
import com.unsa.suppliers.domain.dtos.users.UserUpdateRequest;
import com.unsa.suppliers.domain.entities.UserEntity;
import com.unsa.suppliers.domain.exceptions.roles.RoleNotFoundException;
import com.unsa.suppliers.domain.exceptions.users.UserDuplicatedEmailException;
import com.unsa.suppliers.domain.exceptions.users.UserDuplicatedUsernameException;
import com.unsa.suppliers.domain.exceptions.users.UserNotFoundException;
import com.unsa.suppliers.application.mappers.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserEntity> userEntities = userService.getAllUsers();
        if (userEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(userEntities.stream().map(userMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> findById(@PathVariable("id") Integer id) throws UserNotFoundException {
        UserEntity userEntity = userService.findUserById(id);
        return ResponseEntity.ok(userMapper.entityToResponse(userEntity));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest userRequest, UriComponentsBuilder uriComponentsBuilder) throws UserDuplicatedEmailException, UserDuplicatedUsernameException, RoleNotFoundException {
        UserEntity savedUserEntity = userService.createUser(userMapper.requestToEntity(userRequest));
        URI uri = uriComponentsBuilder.path("/api/users/{id}").buildAndExpand(savedUserEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(userMapper.entityToResponse(savedUserEntity));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid UserUpdateRequest userUpdateRequest) throws UserNotFoundException, UserDuplicatedUsernameException, UserDuplicatedEmailException {
        userService.updateUser(id, userMapper.updateRequestToEntity(userUpdateRequest));
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
