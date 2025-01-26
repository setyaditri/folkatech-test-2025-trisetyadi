package com.example.springboot_restapi_belanja.security;

import com.example.springboot_restapi_belanja.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, 
        @NonNull FilterChain chain)
            throws ServletException, IOException {

        System.out.println("Incoming Request URI: " + request.getRequestURI());
        System.out.println("Authorization Header: " + request.getHeader("Authorization"));

        String servletPath = request.getServletPath();
        if (servletPath.equals("/api/auth/register") || servletPath.equals("/api/auth/login")) {
            System.out.println("Skipping JWT validation for endpoint: " + servletPath);
            chain.doFilter(request, response);
            return;
        }

        String token = getTokenFromHeader(request);
        if (token != null) {
            System.out.println("Token found: " + token);
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractEmail(token);
                String role = jwtUtil.extractClaim(token, claims -> claims.get("role", String.class));
                System.out.println("Token is valid. Username extracted: " + username + ", Role: " + role);

                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                System.out.println("Token is invalid.");
            }
        } else {
            System.out.println("No token found in Authorization header.");
        }

        System.out.println("Filter processing completed for URI: " + request.getRequestURI());
        chain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}