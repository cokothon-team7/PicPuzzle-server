package com.example.cokothon.domain.dto;

public record RegisterRequest(
        String name,
        String email,
        String password
) {
}
