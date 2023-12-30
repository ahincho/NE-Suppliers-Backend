package com.unsa.suppliers.infrastructure.controllers;

import com.unsa.suppliers.application.services.SupplierService;
import com.unsa.suppliers.domain.dtos.suppliers.SupplierRequest;
import com.unsa.suppliers.domain.dtos.suppliers.SupplierResponse;
import com.unsa.suppliers.domain.entities.SupplierEntity;
import com.unsa.suppliers.domain.exceptions.categories.CategoryNotFoundException;
import com.unsa.suppliers.domain.exceptions.countries.CountryNotFoundException;
import com.unsa.suppliers.domain.exceptions.states.StateNotFoundException;
import com.unsa.suppliers.domain.exceptions.suppliers.*;
import com.unsa.suppliers.application.mappers.SupplierMapper;
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
    private final SupplierMapper supplierMapper;
    public SupplierController(SupplierService supplierService, SupplierMapper supplierMapper) {
        this.supplierService = supplierService;
        this.supplierMapper = supplierMapper;
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<SupplierResponse>> getAll() {
        List<SupplierEntity> supplierEntities = supplierService.getAllSuppliers();
        if (supplierEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(supplierEntities.stream().map(supplierMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<SupplierResponse> getById(@PathVariable("id") Integer id) throws SupplierNotFoundException {
        SupplierEntity supplierEntity = supplierService.findSupplierById(id);
        return ResponseEntity.ok(supplierMapper.entityToResponse(supplierEntity));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SupplierResponse> save(@RequestBody @Valid SupplierRequest supplierRequest, UriComponentsBuilder uriComponentsBuilder) throws SupplierDuplicatedNameException, SupplierDuplicatedRucException, CategoryNotFoundException, CountryNotFoundException, StateNotFoundException {
        SupplierEntity supplierEntity = supplierService.createSupplier(supplierMapper.requestToEntity(supplierRequest));
        URI uri = uriComponentsBuilder.path("/api/suppliers/{id}").buildAndExpand(supplierEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(supplierMapper.entityToResponse(supplierEntity));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid SupplierRequest supplierRequest) throws SupplierNotFoundException, SupplierDuplicatedNameException, SupplierDuplicatedRucException, CategoryNotFoundException, CountryNotFoundException {
        supplierService.updateSupplier(id, supplierMapper.requestToEntity(supplierRequest));
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws SupplierNotFoundException, StateNotFoundException {
        supplierService.changeSupplierState(id, "DELETED");
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
