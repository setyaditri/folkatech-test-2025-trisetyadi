package com.example.springboot_restapi_belanja.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String principal;
    private final String token;

    public JwtAuthenticationToken(String principal, String token) {
        super(null);
        this.principal = principal;
        this.token = token;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}