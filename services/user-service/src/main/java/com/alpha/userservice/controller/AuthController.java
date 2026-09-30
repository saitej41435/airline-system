package com.alpha.userservice.controller;

import com.alpha.payload.request.LoginRequest;
import com.alpha.payload.response.AuthResponse;
import com.alpha.payload.dto.UserDTO;
import com.alpha.userservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUp(@Valid @RequestBody UserDTO user) throws Exception{
        return ResponseEntity.ok(authService.signUp(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req) throws Exception{
        return ResponseEntity.ok(authService.login(req.getEmail(), req.getPassword()));
    }
}
