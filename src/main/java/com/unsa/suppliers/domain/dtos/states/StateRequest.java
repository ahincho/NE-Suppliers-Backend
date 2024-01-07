package com.unsa.suppliers.domain.dtos.states;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateRequest {
    @NotBlank(message = "State name is required")
    private String name;
}
