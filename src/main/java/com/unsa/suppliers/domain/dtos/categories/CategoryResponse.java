package com.unsa.suppliers.domain.dtos.categories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Integer id;
    private String name;
}
