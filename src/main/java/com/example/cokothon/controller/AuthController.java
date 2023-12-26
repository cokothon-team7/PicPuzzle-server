package com.example.cokothon.controller;

import com.example.cokothon.domain.dto.RegisterRequest;
import com.example.cokothon.domain.dto.SignInRequest;
import com.example.cokothon.filter.JwtRequestFilter;
import com.example.cokothon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> signIn(@RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);

        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(@RequestBody SignInRequest signInRequest) {
        String token = userService.signIn(signInRequest);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtRequestFilter.AUTHORIZATION_HEADER, JwtRequestFilter.TOKEN_TYPE + token);

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
    }

}
