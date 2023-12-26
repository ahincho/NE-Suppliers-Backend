package com.unsa.suppliers.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must contain at least 3 characters")
    private String name;
    @NotBlank(message = "Lastname must not be blank")
    @Size(min = 3, message = "Lastname must contain at least 3 characters")
    private String lastname;
    @NotBlank(message = "Username must not be blank")
    @Size(min = 4, message = "Username must contain at least 4 characters")
    private String username;
}
