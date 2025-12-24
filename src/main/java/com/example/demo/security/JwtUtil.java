package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.*;

public class JwtUtil {

    private String secret;
    private Long jwtExpirationMs;

    public String generateToken(String username, String role, Long userId, String email) {

        Map<String,Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("userId", userId);
        claims.put("email", email);

        return Jwts.builder()
            .setSubject(username)
            .addClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();
    }

    public Jws<Claims> validateAndGetClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    }
}
