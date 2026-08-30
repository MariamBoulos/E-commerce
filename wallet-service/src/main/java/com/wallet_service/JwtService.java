package com.wallet_service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String secretKey =
            "my-super-secret-key-that-is-at-least-32-characters-long";

    private final SecretKey key =Keys.hmacShaKeyFor(secretKey.getBytes());
    
    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 6))
                .signWith(key)
                .compact();
    }
    
    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token,UserDetails userDetails) {

        String username = extractUsername(token);

        return username.equals(userDetails.getUsername());
    }
}