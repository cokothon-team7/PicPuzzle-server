package com.example.cokothon.domain.dto;

import com.example.cokothon.domain.Puzzle;
import lombok.Builder;

@Builder
public class PuzzleDto {

    private String hint;

    public static PuzzleDto toDto(Puzzle puzzle){
        return PuzzleDto.builder()
                .hint(puzzle.getHint())
                .build();
    }

}
