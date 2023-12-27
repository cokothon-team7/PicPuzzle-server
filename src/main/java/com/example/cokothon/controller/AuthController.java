package com.example.cokothon.controller;

import com.example.cokothon.domain.dto.RegisterRequest;
import com.example.cokothon.domain.dto.SignInRequest;
import com.example.cokothon.filter.JwtRequestFilter;
import com.example.cokothon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final String COOKIE_NAME = "access_token";

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Void> signIn(@RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);

        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(@RequestBody SignInRequest signInRequest) {
        String token = userService.signIn(signInRequest);

        ResponseCookie cookie = ResponseCookie.from(COOKIE_NAME, token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60 * 60 * 24)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(null);

    }

}
