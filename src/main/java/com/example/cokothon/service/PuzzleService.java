package com.example.cokothon.service;

import com.example.cokothon.domain.Puzzle;
import com.example.cokothon.domain.dto.CreatePuzzleRequest;
import com.example.cokothon.repository.PuzzleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PuzzleService {

    private final String IMAGE_BASE_PATH;
    private final PuzzleRepository puzzleRepository;

    public PuzzleService(@Value("${IMAGE_PATH}") String imagePath, PuzzleRepository puzzleRepository) {
        this.IMAGE_BASE_PATH = imagePath;
        this.puzzleRepository = puzzleRepository;
    }

    @Transactional
    public void createPuzzle(CreatePuzzleRequest createPuzzleRequest, MultipartFile image) throws IOException {
        String imagePath = saveImage(image);
        Puzzle puzzle = Puzzle.builder()
                .imagePath(imagePath)
                .hint(createPuzzleRequest.hint())
                .col(createPuzzleRequest.col())
                .row(createPuzzleRequest.row())
                .build();
        puzzleRepository.save(puzzle);
    }

    private String saveImage(MultipartFile image) throws IOException{
        UUID uuid = UUID.randomUUID();
        String filename = uuid + getExtensionType(image.getOriginalFilename());
        String fullPath = IMAGE_BASE_PATH + filename;
        log.info("filename: {}", filename);
        image.transferTo(new File(fullPath));
        return filename;
    }

    private String getExtensionType(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}
