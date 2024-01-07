package com.unsa.suppliers.domain.dtos.suppliers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequest {
    @NotBlank(message = "Supplier name is required")
    private String name;
    @NotBlank(message = "RUC number is required")
    @Size(min = 11, max = 11, message = "RUC number must contain 11 digits")
    private String ruc;
    @NotNull(message = "Category identifier is required")
    @Positive(message = "Category identifier must be greater than 0")
    private Integer categoryId;
    @NotNull(message = "Country identifier is required")
    @Positive(message = "Country identifier must be greater than 0")
    private Integer countryId;
}
