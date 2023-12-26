package com.example.cokothon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @GetMapping("/")
    public ResponseEntity<?> showQuiz() {
        return
    }

    @PostMapping("/post")
    public ResponseEntity<?> postQuiz() {

    }
}
