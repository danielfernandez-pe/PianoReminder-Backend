package com.dfernandezyopla.PianoReminder.Auth.Controllers;

import com.dfernandezyopla.PianoReminder.Auth.DTOs.AuthRequestDTO;
import com.dfernandezyopla.PianoReminder.Auth.DTOs.AuthResponseDTO;
import com.dfernandezyopla.PianoReminder.Auth.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<AuthResponseDTO> register(@RequestBody AuthRequestDTO request) {
        String token = authService.registerUser(request.getEmail(), request.getPassword());
        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        return new ResponseEntity<AuthResponseDTO>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO request) {
        String token = authService.authenticateUser(request.getEmail(), request.getPassword());
        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        return response;
    }
}