package com.unsa.suppliers.infrastructure.controllers;

import com.unsa.suppliers.application.services.SupplierService;
import com.unsa.suppliers.domain.dtos.suppliers.SupplierRequest;
import com.unsa.suppliers.domain.dtos.suppliers.SupplierResponse;
import com.unsa.suppliers.domain.entities.SupplierEntity;
import com.unsa.suppliers.domain.exceptions.states.StateNotFoundException;
import com.unsa.suppliers.domain.exceptions.suppliers.SupplierDuplicatedException;
import com.unsa.suppliers.domain.exceptions.suppliers.SupplierNotFoundException;
import com.unsa.suppliers.domain.mappers.SupplierMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final SupplierService supplierService;
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<SupplierResponse>> getAll() {
        List<SupplierEntity> supplierEntities = supplierService.getAllSuppliers();
        if (supplierEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(supplierEntities.stream().map(SupplierMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<SupplierResponse> getById(@PathVariable("id") Integer id) throws SupplierNotFoundException {
        SupplierEntity supplierEntity = supplierService.findSupplierById(id);
        return ResponseEntity.ok(SupplierMapper.entityToResponse(supplierEntity));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SupplierResponse> save(@RequestBody @Valid SupplierRequest supplierRequest, UriComponentsBuilder uriComponentsBuilder) throws SupplierDuplicatedException, StateNotFoundException {
        SupplierEntity supplierEntity = supplierService.createSupplier(SupplierMapper.requestToEntity(supplierRequest));
        URI uri = uriComponentsBuilder.path("/api/suppliers/{id}").buildAndExpand(supplierEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(SupplierMapper.entityToResponse(supplierEntity));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid SupplierRequest supplierRequest) throws SupplierNotFoundException, SupplierDuplicatedException {
        supplierService.updateSupplier(id, SupplierMapper.requestToEntity(supplierRequest));
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws SupplierNotFoundException, StateNotFoundException {
        supplierService.changeSupplierState(id, "DELETE");
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/inactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> inactivate(@PathVariable("id") Integer id) throws SupplierNotFoundException, StateNotFoundException {
        supplierService.changeSupplierState(id, "INACTIVE");
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/reactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> reactivate(@PathVariable("id") Integer id) throws SupplierNotFoundException, StateNotFoundException {
        supplierService.changeSupplierState(id, "ACTIVE");
        return ResponseEntity.noContent().build();
    }
}
