package com.unsa.suppliers.domain.dtos.countries;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryResponse {
    private Integer id;
    private String name;
}
