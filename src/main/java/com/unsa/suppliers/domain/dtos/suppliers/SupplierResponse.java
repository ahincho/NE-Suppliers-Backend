package com.unsa.suppliers.domain.dtos.suppliers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponse {
    private Integer id;
    private String name;
    private String ruc;
    private Integer categoryId;
    private String categoryName;
    private Integer countryId;
    private Integer countryName;
    private Integer stateId;
    private String stateName;
}
