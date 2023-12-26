package com.example.cokothon.domain.dto;

import lombok.Builder;

@Builder
public record SignInRequest(
   String email,
   String password
) {}
