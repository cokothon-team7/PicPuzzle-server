package com.example.cokothon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @GetMapping("/")
    public ResponseEntity<?> showQuiz() {
        return null;
    }

    @PostMapping("/post")
    public ResponseEntity<?> postQuiz() {
        return null;
    }
}
