package com.example.cokothon.controller;

import com.example.cokothon.domain.dto.CreatePuzzleRequest;
import com.example.cokothon.service.PuzzleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/api/puzzles")
@RequiredArgsConstructor
public class PuzzleController {

    public final PuzzleService puzzleService;

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

}
