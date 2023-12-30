package com.unsa.suppliers.application.mappers;

import com.unsa.suppliers.domain.dtos.suppliers.SupplierRequest;
import com.unsa.suppliers.domain.dtos.suppliers.SupplierResponse;
import com.unsa.suppliers.domain.entities.CategoryEntity;
import com.unsa.suppliers.domain.entities.CountryEntity;
import com.unsa.suppliers.domain.entities.SupplierEntity;
import com.unsa.suppliers.domain.exceptions.categories.CategoryNotFoundException;
import com.unsa.suppliers.domain.exceptions.countries.CountryNotFoundException;
import com.unsa.suppliers.domain.repositories.CategoryRepository;
import com.unsa.suppliers.domain.repositories.CountryRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class SupplierMapper {
    private final CategoryRepository categoryRepository;
    private final CountryRepository countryRepository;
    public SupplierMapper(CategoryRepository categoryRepository, CountryRepository countryRepository) {
        this.categoryRepository = categoryRepository;
        this.countryRepository = countryRepository;
    }
    public SupplierResponse entityToResponse(SupplierEntity supplierEntity) {
        return SupplierResponse.builder()
                .id(supplierEntity.getId())
                .name(supplierEntity.getName())
                .ruc(supplierEntity.getRuc())
                .categoryId(supplierEntity.getCategory().getId())
                .categoryName(supplierEntity.getCategory().getName())
                .countryId(supplierEntity.getCountry().getId())
                .countryName(supplierEntity.getCountry().getName())
                .stateId(supplierEntity.getState().getId())
                .stateName(supplierEntity.getState().getName())
                .build();
    }
    public SupplierEntity requestToEntity(SupplierRequest supplierRequest) throws CategoryNotFoundException, CountryNotFoundException {
        return SupplierEntity.builder()
                .name(supplierRequest.getName())
                .ruc(supplierRequest.getRuc())
                .category(findCategoryByIdOrThrowNotFound(supplierRequest.getCategoryId()))
                .country(findCountryByIdOrThrowNotFound(supplierRequest.getCountryId()))
                .build();
    }
    // Private Auxiliary Methods because Boiler Code
    private CategoryEntity findCategoryByIdOrThrowNotFound(Integer id) throws CategoryNotFoundException {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) { throw new CategoryNotFoundException(); }
        return optionalCategory.get();
    }
    private CountryEntity findCountryByIdOrThrowNotFound(Integer id) throws CountryNotFoundException {
        Optional<CountryEntity> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isEmpty()) { throw new CountryNotFoundException(); }
        return optionalCountry.get();
    }
}
