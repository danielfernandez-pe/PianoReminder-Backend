package com.dfernandezyopla.PianoReminder.Auth.Controllers;

import com.dfernandezyopla.PianoReminder.Auth.DTOs.AuthRequestDTO;
import com.dfernandezyopla.PianoReminder.Auth.DTOs.LoginResponseDTO;
import com.dfernandezyopla.PianoReminder.Auth.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    final private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody AuthRequestDTO request) {
        authService.registerUser(request.getEmail(), request.getPassword());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody AuthRequestDTO request) {
        String token = authService.authenticateUser(request.getEmail(), request.getPassword());
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        return response;
    }
}