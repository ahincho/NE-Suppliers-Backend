package com.unsa.suppliers.domain.dtos.users;

import jakarta.validation.constraints.Email;
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
    @NotBlank(message = "User name is required")
    @Size(min = 2, message = "User name must contain at least 2 characters")
    private String name;
    @NotBlank(message = "User lastname is required")
    @Size(min = 2, message = "Lastname must contain at least 2 characters")
    private String lastname;
    @NotBlank(message = "User username or nickname is required")
    @Size(min = 4, message = "Username must contain at least 4 characters")
    private String username;
    @NotBlank(message = "User email is required")
    @Email(message = "Please send a valid email")
    private String email;
}
