package com.inventory_service;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String secretKey =
            "my-super-secret-key-that-is-at-least-32-characters-long";

    private final SecretKey key =
            Keys.hmacShaKeyFor(secretKey.getBytes());

    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build() 
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
