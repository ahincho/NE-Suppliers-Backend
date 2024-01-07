package com.unsa.suppliers.infrastructure.controllers;

import com.unsa.suppliers.application.mappers.StateMapper;
import com.unsa.suppliers.application.services.StateService;
import com.unsa.suppliers.domain.dtos.states.StateRequest;
import com.unsa.suppliers.domain.dtos.states.StateResponse;
import com.unsa.suppliers.domain.entities.StateEntity;
import com.unsa.suppliers.domain.exceptions.states.StateDuplicatedException;
import com.unsa.suppliers.domain.exceptions.states.StateInUseException;
import com.unsa.suppliers.domain.exceptions.states.StateNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/states")
public class StateController {
    private final StateService stateService;
    private final StateMapper stateMapper;
    public StateController(StateService stateService, StateMapper stateMapper) {
        this.stateService = stateService;
        this.stateMapper = stateMapper;
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StateResponse>> getAll() {
        List<StateEntity> stateEntities = stateService.getAllStates();
        if (stateEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(stateEntities.stream().map(stateMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StateResponse> getById(@PathVariable("id") Integer id) throws StateNotFoundException {
        return ResponseEntity.ok(stateMapper.entityToResponse(stateService.getStateById(id)));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StateResponse> save(@RequestBody @Valid StateRequest stateRequest, UriComponentsBuilder uriComponentsBuilder) throws StateDuplicatedException {
        StateEntity stateEntity = stateService.createState(stateMapper.requestToEntity(stateRequest));
        URI uri = uriComponentsBuilder.path("/api/states/{id}").buildAndExpand(stateEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(stateMapper.entityToResponse(stateEntity));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid StateRequest stateRequest) throws StateNotFoundException, StateDuplicatedException {
        stateService.updateState(id, stateMapper.requestToEntity(stateRequest));
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws StateInUseException, StateNotFoundException {
        stateService.deleteState(id);
        return ResponseEntity.notFound().build();
    }
}
