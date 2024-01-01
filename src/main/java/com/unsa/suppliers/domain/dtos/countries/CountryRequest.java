package com.unsa.suppliers.domain.dtos.countries;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryRequest {
    @NotBlank(message = "Name field is required")
    private String name;
}
