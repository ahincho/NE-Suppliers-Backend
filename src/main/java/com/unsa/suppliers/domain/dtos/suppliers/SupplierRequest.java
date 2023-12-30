package com.unsa.suppliers.domain.dtos.suppliers;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Name field is required")
    private String name;
    @NotBlank(message = "RUC field is required")
    @Size(min = 11, max = 11, message = "RUC field must contain 11 characters")
    private String ruc;
    @NotBlank(message = "Category field is required")
    @Positive(message = "Category field must be greater than 0")
    private Integer categoryId;
    @NotBlank(message = "Country field is required")
    @Positive(message = "Country field must be greater than 0")
    private Integer countryId;
}
