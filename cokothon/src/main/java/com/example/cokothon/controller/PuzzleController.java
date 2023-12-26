package com.example.cokothon.controller;

import com.example.cokothon.domain.Puzzle;
import com.example.cokothon.domain.dto.CreatePuzzleRequest;
import com.example.cokothon.domain.dto.PuzzleDto;
import com.example.cokothon.repository.PuzzleRepository;
import com.example.cokothon.service.PuzzleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/api/puzzles")
@RequiredArgsConstructor
public class PuzzleController {

    public final PuzzleService puzzleService;
    public final PuzzleRepository puzzleRepository;

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createPuzzle(@RequestPart(name = "json") CreatePuzzleRequest createPuzzleRequest,
                                          @RequestPart(name = "image") MultipartFile multipartFile) {
        try {
            puzzleService.createPuzzle(createPuzzleRequest, multipartFile);
        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/downloadFile/{file}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getFile(@PathVariable("file") Long id) throws IOException {
        Optional<Puzzle> puzzle = puzzleRepository.findById(id);
        Resource resource = new FileSystemResource("/Users/cheesecrust/Desktop/" + puzzle.get().getImagePath());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping(value = "/quiz/{file}")
    public ResponseEntity<?> getContent(@PathVariable("file") Long id){
        Optional<Puzzle> puzzle = puzzleRepository.findById(id);

        return new ResponseEntity<>(puzzle, HttpStatus.OK);
    }
}
