package com.example.onecar.dto.records;

import jakarta.validation.constraints.NotBlank;

public record UsernamePassword(
   @NotBlank(message = "Username should not be empty")
   String username,
   @NotBlank(message = "Password should not be empty")
   String password
) {}
