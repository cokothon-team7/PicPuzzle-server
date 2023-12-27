package com.example.cokothon.domain.dto;

import org.springframework.web.multipart.MultipartFile;

public record CreatePuzzleRequest(
        Long col,
        Long row,
        String category,
        String hint,
        String message) {
}
