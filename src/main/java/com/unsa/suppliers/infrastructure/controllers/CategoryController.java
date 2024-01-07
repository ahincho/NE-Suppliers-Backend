package com.unsa.suppliers.infrastructure.controllers;

import static com.unsa.suppliers.application.services.StateService.*;

import com.unsa.suppliers.application.mappers.CategoryMapper;
import com.unsa.suppliers.application.services.CategoryService;
import com.unsa.suppliers.domain.dtos.categories.CategoryRequest;
import com.unsa.suppliers.domain.dtos.categories.CategoryResponse;
import com.unsa.suppliers.domain.entities.CategoryEntity;
import com.unsa.suppliers.domain.exceptions.categories.CategoryDuplicatedException;
import com.unsa.suppliers.domain.exceptions.categories.CategoryNotFoundException;
import com.unsa.suppliers.domain.exceptions.states.StateNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<CategoryResponse>> getAll() {
        List<CategoryEntity> categoryEntities = categoryService.getAllCategories();
        if (categoryEntities.isEmpty()) { return ResponseEntity.noContent().build(); }
        return ResponseEntity.ok(categoryEntities.stream().map(categoryMapper::entityToResponse).toList());
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<CategoryResponse> getById(@PathVariable("id") Integer id) throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryMapper.entityToResponse(categoryService.getCategoryById(id)));
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<CategoryResponse> save(@RequestBody @Valid CategoryRequest categoryRequest, UriComponentsBuilder uriComponentsBuilder) throws CategoryDuplicatedException, StateNotFoundException {
        CategoryEntity categoryEntity = categoryService.createCategory(categoryMapper.requestToEntity(categoryRequest));
        URI uri = uriComponentsBuilder.path("/api/categories/{id}").buildAndExpand(categoryEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(categoryMapper.entityToResponse(categoryEntity));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody @Valid CategoryRequest categoryRequest) throws CategoryDuplicatedException, CategoryNotFoundException {
        categoryService.updateCategory(id, categoryMapper.requestToEntity(categoryRequest));
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws StateNotFoundException, CategoryNotFoundException {
        categoryService.changeCategoryState(id, DELETED_STATE);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/disable/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> disable(@PathVariable("id") Integer id) throws StateNotFoundException, CategoryNotFoundException {
        categoryService.changeCategoryState(id, DISABLED_STATE);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/enable/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> enable(@PathVariable("id") Integer id) throws StateNotFoundException, CategoryNotFoundException {
        categoryService.changeCategoryState(id, ACTIVE_STATE);
        return ResponseEntity.noContent().build();
    }
}
