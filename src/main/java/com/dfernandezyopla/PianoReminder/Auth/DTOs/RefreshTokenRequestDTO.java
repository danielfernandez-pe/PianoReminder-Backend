package com.dfernandezyopla.PianoReminder.Auth.DTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class RefreshTokenRequestDTO {
    @Valid
    @NotBlank
    String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
