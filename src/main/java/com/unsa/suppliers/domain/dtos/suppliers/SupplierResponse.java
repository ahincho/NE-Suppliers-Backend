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
    private String category;
    private String country;
    private String state;
}
