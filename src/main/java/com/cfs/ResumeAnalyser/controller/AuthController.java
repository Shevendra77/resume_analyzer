
        package com.cfs.ResumeAnalyser.controller;

import com.cfs.ResumeAnalyser.dto.LoginRequest;
import com.cfs.ResumeAnalyser.dto.LoginResponse;
import com.cfs.ResumeAnalyser.dto.RegisterRequest;
import com.cfs.ResumeAnalyser.model.User;
import com.cfs.ResumeAnalyser.repository.UserRepository;
import com.cfs.ResumeAnalyser.security.JwtUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        if (userRepository
                .findByEmail(request.getEmail())
                .isPresent()) {

            return "Email already exists";
        }

        User user = new User(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(
                        request.getPassword()),
                "USER"
        );

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        User user = userRepository
                .findByEmail(
                        request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid Password");
        }

        String token =
                jwtUtil.generateToken(
                        user.getEmail(),
                        user.getRole());

        return new LoginResponse(
                token,
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
