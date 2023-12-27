package com.example.cokothon.service;

import com.example.cokothon.domain.Puzzle;
import com.example.cokothon.domain.dto.CreatePuzzleRequest;
import com.example.cokothon.repository.PuzzleRepository;
import com.example.cokothon.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.User;
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

    public final String IMAGE_BASE_PATH;
    public final PuzzleRepository puzzleRepository;
    private final UserRepository userRepository;

    public PuzzleService(@Value("${IMAGE_PATH}") String imagePath, PuzzleRepository puzzleRepository,
            UserRepository userRepository) {
        this.IMAGE_BASE_PATH = imagePath;
        this.puzzleRepository = puzzleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Puzzle createPuzzle(CreatePuzzleRequest createPuzzleRequest, MultipartFile image, User user)
            throws IOException {
        String imagePath = saveImage(image);
        com.example.cokothon.domain.User _user = userRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Puzzle puzzle = Puzzle.builder()
                .imagePath(imagePath)
                .hint(createPuzzleRequest.hint())
                .category(createPuzzleRequest.category())
                .message(createPuzzleRequest.message())
                .user(_user)
                .col(createPuzzleRequest.col())
                .row(createPuzzleRequest.row())
                .build();
        puzzleRepository.save(puzzle);
        return puzzle;
    }

    private String saveImage(MultipartFile image) throws IOException {
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
