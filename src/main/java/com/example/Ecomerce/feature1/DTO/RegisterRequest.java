package com.example.Ecomerce.feature1.DTO;

import com.example.Ecomerce.feature1.Eums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank String email,
        @NotBlank @Size(min = 6) String password,
        Role role // Optionnel, par défaut USER
) {}
