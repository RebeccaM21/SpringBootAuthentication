package com.project.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor // will create constructor using any private field within the class
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, // can intercept data within the request and in the response
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain // contains list of filters
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization"); // this header will contain the JWT
        final String jwtToken;
        final String userEmaiL;
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7); // to extract the jwt token
        userEmaiL = jwtService.extractUsername(jwtToken);// to extract the userEmail
                // position 7 because considering "Bearer" at start of authHeader
    }
}
