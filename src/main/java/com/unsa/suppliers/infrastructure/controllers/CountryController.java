package com.unsa.suppliers.infrastructure.controllers;

import static com.unsa.suppliers.application.services.StateService.*;

import com.unsa.suppliers.application.mappers.CountryMapper;
import com.unsa.suppliers.application.services.CountryService;
import com.unsa.suppliers.domain.dtos.countries.CountryRequest;
import com.unsa.suppliers.domain.dtos.countries.CountryResponse;
import com.unsa.suppliers.domain.entities.CountryEntity;
import com.unsa.suppliers.domain.exceptions.countries.CountryDuplicatedException;
import com.unsa.suppliers.domain.exceptions.countries.CountryNotFoundException;
import com.unsa.suppliers.domain.exceptions.states.StateNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;
    private final CountryMapper countryMapper;
    public CountryController(CountryService countryService, CountryMapper countryMapper) {
        this.countryService = countryService;
        this.countryMapper = countryMapper;
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<CountryResponse>> getAll() {
        List<CountryEntity> countryEntities = countryService.getAllCountries();
        if (countryEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(countryEntities.stream().map(countryMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<CountryResponse> getById(@PathVariable("id") Integer id) throws CountryNotFoundException {
        return ResponseEntity.ok(countryMapper.entityToResponse(countryService.getCountryById(id)));
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<CountryResponse> save(@RequestBody @Valid CountryRequest countryRequest, UriComponentsBuilder uriComponentsBuilder) throws CountryDuplicatedException {
        CountryEntity countryEntity = countryService.createCountry(countryMapper.requestToEntity(countryRequest));
        URI uri = uriComponentsBuilder.path("/api/countries/{id]").buildAndExpand(countryEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(countryMapper.entityToResponse(countryEntity));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid CountryRequest countryRequest) throws CountryDuplicatedException, CountryNotFoundException {
        countryService.updateCountry(id, countryMapper.requestToEntity(countryRequest));
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws StateNotFoundException, CountryNotFoundException {
        countryService.changeCountryState(id, DELETED_STATE);
        return ResponseEntity.notFound().build();
    }
    @PatchMapping("/{id}/disable")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> disable(@PathVariable("id") Integer id) throws StateNotFoundException, CountryNotFoundException {
        countryService.changeCountryState(id, DISABLED_STATE);
        return ResponseEntity.notFound().build();
    }
    @PatchMapping("/{id}/enable")
    public ResponseEntity<Void> reactivate(@PathVariable("id") Integer id) throws StateNotFoundException, CountryNotFoundException {
        countryService.changeCountryState(id, ACTIVE_STATE);
        return ResponseEntity.notFound().build();
    }
}
