package com.unsa.suppliers.domain.dtos.roles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {
    private Integer id;
    private String name;
}
