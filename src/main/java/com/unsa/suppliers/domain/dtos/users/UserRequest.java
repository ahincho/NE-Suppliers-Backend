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
public class UserRequest {
    @NotBlank(message = "Name field is required")
    @Size(min = 3, message = "Name must contain at least 3 characters")
    private String name;
    @NotBlank(message = "Lastname field is required")
    @Size(min = 3, message = "Lastname must contain at least 3 characters")
    private String lastname;
    @NotBlank(message = "Username field is required")
    @Size(min = 4, message = "Username must contain at least 4 characters")
    private String username;
    @NotBlank(message = "Email field is required")
    @Email(message = "Please send a valid email")
    private String email;
    @NotBlank(message = "Password field is required")
    @Size(min = 8, message = "Password must contain at least 8 characters")
    private String password;
}
