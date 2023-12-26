package com.example.cokothon.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Getter
public class UserAdapter extends org.springframework.security.core.userdetails.User {

    private final User user;

    public UserAdapter(User user) {
        super(user.getEmail(), user.getPassword(), convertAuthorities(user.getAuthority()));
        this.user = user;
    }

    private static List<GrantedAuthority> convertAuthorities(Authority authority) {
        return List.of(authority);
    }
}
