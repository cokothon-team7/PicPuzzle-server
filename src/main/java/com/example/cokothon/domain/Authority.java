package com.example.cokothon.domain;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public enum Authority implements GrantedAuthority{

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

}
