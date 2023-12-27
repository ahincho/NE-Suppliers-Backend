package com.unsa.suppliers.domain.mappers;

import com.unsa.suppliers.domain.dtos.suppliers.SupplierRequest;
import com.unsa.suppliers.domain.dtos.suppliers.SupplierResponse;
import com.unsa.suppliers.domain.entities.SupplierEntity;

public class SupplierMapper {
    public static SupplierResponse entityToResponse(SupplierEntity supplierEntity) {
        return SupplierResponse.builder()
                .id(supplierEntity.getId())
                .name(supplierEntity.getName())
                .stateId(supplierEntity.getState().getId())
                .stateName(supplierEntity.getState().getName())
                .build();
    }
    public static SupplierEntity requestToEntity(SupplierRequest supplierRequest) {
        return SupplierEntity.builder()
                .name(supplierRequest.getName())
                .build();
    }
}
