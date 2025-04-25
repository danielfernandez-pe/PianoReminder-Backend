package com.dfernandezyopla.PianoReminder.Auth.Controllers;

import com.dfernandezyopla.PianoReminder.Auth.DTOs.AuthRequestDTO;
import com.dfernandezyopla.PianoReminder.Auth.DTOs.AuthResponseDTO;
import com.dfernandezyopla.PianoReminder.Auth.DTOs.RefreshTokenRequestDTO;
import com.dfernandezyopla.PianoReminder.Auth.Entities.User;
import com.dfernandezyopla.PianoReminder.Auth.Entities.UserToken;
import com.dfernandezyopla.PianoReminder.Auth.Services.AuthService;
import com.dfernandezyopla.PianoReminder.Auth.Services.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    final private AuthService authService;
    final private RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(AuthService authService, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody AuthRequestDTO request) {
        UserToken userToken = authService.registerUser(request.getEmail(), request.getPassword());
        String refreshToken = refreshTokenService.generateRefreshToken(userToken.getUser());
        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(userToken.getToken());
        response.setRefreshToken(refreshToken);
        return new ResponseEntity<AuthResponseDTO>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO request) {
        UserToken userToken = authService.authenticateUser(request.getEmail(), request.getPassword());
        String refreshToken = refreshTokenService.generateRefreshToken(userToken.getUser());
        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(userToken.getToken());
        response.setRefreshToken(refreshToken);
        return response;
    }

    @PostMapping("/refresh")
    public AuthResponseDTO refreshToken(@Valid  @RequestBody RefreshTokenRequestDTO request) {
        User user = refreshTokenService.validateRefreshTokenAndGetUser(request.getRefreshToken());
        String token = authService.generateToken(user);
        String updatedRefreshToken = refreshTokenService.generateRefreshToken(user);
        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        response.setRefreshToken(updatedRefreshToken);
        return response;
    }
}