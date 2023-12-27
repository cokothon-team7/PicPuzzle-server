package com.example.cokothon.controller;

import com.example.cokothon.domain.Puzzle;
import com.example.cokothon.domain.UserAdapter;
import com.example.cokothon.repository.PuzzleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final PuzzleRepository puzzleRepository;

    @GetMapping("/me/puzzles")
    public ResponseEntity<List<Puzzle>> getAllPuzzles(
            @AuthenticationPrincipal User user
            ) {
        List<Puzzle> puzzles = puzzleRepository.findAllByUser(user);
        return ResponseEntity.ok(puzzles);
    }
}
