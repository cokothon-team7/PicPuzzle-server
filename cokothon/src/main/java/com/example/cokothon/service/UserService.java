package com.example.cokothon.service;

import com.example.cokothon.domain.Authority;
import com.example.cokothon.domain.User;
import com.example.cokothon.domain.dto.RegisterRequest;
import com.example.cokothon.domain.dto.SignInRequest;
import com.example.cokothon.repository.UserRepository;
import com.example.cokothon.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegisterRequest registerRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(registerRequest.email());

        if (optionalUser.isPresent()) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(registerRequest.password());

        User user = User.builder()
                .email(registerRequest.email())
                .password(encodedPassword)
                .authority(Authority.USER)
                .build();

        userRepository.save(user);
    }

    @Transactional
    public String signIn(SignInRequest signInRequest) {
        String email = signInRequest.email();
        String password = signInRequest.password();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateToken(email);
    }
}
