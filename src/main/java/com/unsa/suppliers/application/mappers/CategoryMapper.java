package com.unsa.suppliers.application.mappers;

import com.unsa.suppliers.domain.dtos.categories.CategoryRequest;
import com.unsa.suppliers.domain.dtos.categories.CategoryResponse;
import com.unsa.suppliers.domain.entities.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponse entityToResponse(CategoryEntity categoryEntity) {
        return CategoryResponse.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .state(categoryEntity.getState().getName())
                .build();
    }
    public CategoryEntity requestToEntity(CategoryRequest categoryRequest) {
        return CategoryEntity.builder()
                .name(categoryRequest.getName())
                .build();
    }
}
