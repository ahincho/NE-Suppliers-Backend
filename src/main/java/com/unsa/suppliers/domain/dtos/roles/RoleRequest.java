package com.unsa.suppliers.domain.dtos.roles;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {
    @NotBlank(message = "Name must not be blank")
    private String name;
}
