package com.example.cokothon.controller;

import com.example.cokothon.domain.Puzzle;
import com.example.cokothon.domain.dto.CreatePuzzleRequest;
import com.example.cokothon.domain.dto.CreatePuzzleResponse;
import com.example.cokothon.repository.PuzzleRepository;
import com.example.cokothon.service.PuzzleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/api/puzzles")
@RequiredArgsConstructor
public class PuzzleController {

    public final PuzzleService puzzleService;
    private final PuzzleRepository puzzleRepository;

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CreatePuzzleResponse> createPuzzle(@RequestPart(name = "json") CreatePuzzleRequest createPuzzleRequest,
                                                             @RequestPart(name = "image") MultipartFile multipartFile,
                                                             @AuthenticationPrincipal User user) {
        Puzzle puzzle;
        try {
            puzzle = puzzleService.createPuzzle(createPuzzleRequest, multipartFile, user);
        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new CreatePuzzleResponse(puzzle.getId()));
    }

    @GetMapping(value = "/{puzzleId}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getPuzzleImage(@PathVariable("puzzleId") Long puzzleId) {
        Puzzle puzzle = puzzleRepository.findById(puzzleId)
                .orElseThrow(() -> new RuntimeException("사진 없음."));
        Resource resource = new FileSystemResource("/home/hsukju/dev/cokothon/backend/images/" + puzzle.getImagePath());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping(value = "/{puzzleId}")
    public ResponseEntity<?> getContent(@PathVariable("puzzleId") Long puzzleId) {
        Puzzle puzzle = puzzleRepository.findById(puzzleId)
                .orElseThrow(() -> new RuntimeException("사진 없음."));
        return new ResponseEntity<>(puzzle, HttpStatus.OK);
    }
}
