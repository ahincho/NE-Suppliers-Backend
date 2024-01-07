package com.unsa.suppliers.application.mappers;

import com.unsa.suppliers.domain.dtos.suppliers.*;
import com.unsa.suppliers.domain.entities.CategoryEntity;
import com.unsa.suppliers.domain.entities.CountryEntity;
import com.unsa.suppliers.domain.entities.SupplierEntity;
import com.unsa.suppliers.domain.exceptions.categories.CategoryNotFoundException;
import com.unsa.suppliers.domain.exceptions.countries.CountryNotFoundException;
import com.unsa.suppliers.domain.repositories.CategoryRepository;
import com.unsa.suppliers.domain.repositories.CountryRepository;
import org.springframework.stereotype.Component;

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
                .category(supplierEntity.getCategory().getName())
                .country(supplierEntity.getCountry().getName())
                .state(supplierEntity.getState().getName())
                .build();
    }
    public SupplierEntity requestToEntity(SupplierRequest supplierRequest) throws CategoryNotFoundException, CountryNotFoundException {
        return SupplierEntity.builder()
                .name(supplierRequest.getName())
                .ruc(supplierRequest.getRuc())
                .category(findCategoryById(supplierRequest.getCategoryId()))
                .country(findCountryById(supplierRequest.getCountryId()))
                .build();
    }
    // Private Auxiliary Methods because Boiler Code
    private CategoryEntity findCategoryById(Integer id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }
    private CountryEntity findCountryById(Integer id) throws CountryNotFoundException {
        return countryRepository.findById(id).orElseThrow(CountryNotFoundException::new);
    }
}
