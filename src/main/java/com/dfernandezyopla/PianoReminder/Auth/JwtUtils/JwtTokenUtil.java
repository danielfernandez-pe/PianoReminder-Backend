package com.dfernandezyopla.PianoReminder.Auth.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;

@Component
public class JwtTokenUtil {
    private SecretKey secretKey;
    private static final String SECRET_KEY_BASE64 = System.getenv("JWT_SECRET_KEY");

    @PostConstruct
    public void init() {
        secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY_BASE64));
    }

    public String generateToken(String email) {
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(3600); // in an hour

        return Jwts.builder()
                .claims(Map.of(
                        "sub", email,
                        "iat", now.getEpochSecond(),
                        "exp", expiration.getEpochSecond()
                ))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public boolean validateToken(String token, String email) {
        try {
            Claims claims = getClaims(token);
            String extractedEmail = claims.get("sub", String.class);
            Instant expiration = Instant.ofEpochSecond(claims.get("exp", Long.class));
            return extractedEmail.equals(email) && expiration.isAfter(Instant.now());
        } catch (JwtException e) {
            return false;
        }
    }

    public String getEmail(String token) {
        Claims claims = getClaims(token);
        return claims.get("sub", String.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
