package com.cfs.ResumeAnalyser.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        System.out.println("===== JWT FILTER START =====");
        System.out.println("URL: " + request.getRequestURI());
        System.out.println("Authorization Header: " + header);

        try {

            if (header != null && header.startsWith("Bearer ")) {

                String token = header.substring(7);

                if (jwtUtil.validateToken(token)) {

                    String email = jwtUtil.getEmail(token);
                    String role = jwtUtil.getRole(token);

                    System.out.println("Email: " + email);
                    System.out.println("Raw Role: " + role);

                    // 🔥 FIX: normalize role format
                    if (role != null && role.startsWith("ROLE_")) {
                        role = role.substring(5);
                    }

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    email,
                                    null,
                                    AuthorityUtils.createAuthorityList("ROLE_" + role)
                            );

                    SecurityContextHolder.getContext().setAuthentication(auth);

                    System.out.println("Authentication SUCCESS");
                } else {
                    System.out.println("Invalid JWT Token");
                }
            }

        } catch (Exception e) {
            System.out.println("JWT FILTER ERROR: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}