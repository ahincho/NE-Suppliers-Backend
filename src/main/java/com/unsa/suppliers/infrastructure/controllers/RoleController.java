package com.unsa.suppliers.infrastructure.controllers;

import com.unsa.suppliers.application.services.RoleService;
import com.unsa.suppliers.domain.dtos.roles.RoleRequest;
import com.unsa.suppliers.domain.dtos.roles.RoleResponse;
import com.unsa.suppliers.domain.entities.RoleEntity;
import com.unsa.suppliers.domain.exceptions.roles.RoleDuplicatedException;
import com.unsa.suppliers.domain.exceptions.roles.RoleNotFoundException;
import com.unsa.suppliers.application.mappers.RoleMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoleResponse>> getAll() {
        List<RoleEntity> roleEntities = roleService.getAllRoles();
        if (roleEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(roleEntities.stream().map(RoleMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleResponse> getById(@PathVariable("id") Integer id) throws RoleNotFoundException {
        RoleEntity roleEntity = roleService.getRoleById(id);
        return ResponseEntity.ok(RoleMapper.entityToResponse(roleEntity));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleResponse> save(@RequestBody @Valid RoleRequest roleRequest, UriComponentsBuilder uriComponentsBuilder) throws RoleDuplicatedException {
        RoleEntity roleEntity = roleService.createRole(RoleMapper.requestToEntity(roleRequest));
        URI uri = uriComponentsBuilder.path("/api/roles/{id}").buildAndExpand(roleEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(RoleMapper.entityToResponse(roleEntity));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid RoleRequest roleRequest) throws RoleNotFoundException, RoleDuplicatedException {
        roleService.updateRole(id, RoleMapper.requestToEntity(roleRequest));
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws RoleNotFoundException {
        roleService.deleteRole(id);
        return ResponseEntity.notFound().build();
    }
}
