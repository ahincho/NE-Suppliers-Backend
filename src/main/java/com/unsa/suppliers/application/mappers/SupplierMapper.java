package com.unsa.suppliers.domain.mappers;

import com.unsa.suppliers.domain.dtos.suppliers.SupplierRequest;
import com.unsa.suppliers.domain.dtos.suppliers.SupplierResponse;
import com.unsa.suppliers.domain.entities.SupplierEntity;
import org.springframework.stereotype.Service;

public class SupplierMapper {
    public SupplierResponse entityToResponse(SupplierEntity supplierEntity) {
        return SupplierResponse.builder()
                .id(supplierEntity.getId())
                .name(supplierEntity.getName())
                .categoryId(supplierEntity.getCategory().getId())
                .categoryName(supplierEntity.getCategory().getName())
                .countryId(supplierEntity.getCountry().getId())
                .countryName(supplierEntity.getCountry().getName())
                .stateId(supplierEntity.getState().getId())
                .stateName(supplierEntity.getState().getName())
                .build();
    }
    public SupplierEntity requestToEntity(SupplierRequest supplierRequest) {
        return SupplierEntity.builder()
                .name(supplierRequest.getName())
                .ruc(supplierRequest.getRuc())
                .country()
                .build();
    }
}
