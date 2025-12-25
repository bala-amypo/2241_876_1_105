package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {

    private String secret;
    private Long jwtExpirationMs;

    public String generateToken(String username, String role, Long userId, String email) {

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("userId", userId)
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> validateAndGetClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token);
    }
}


// package com.example.demo.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.JwtException;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;

// import java.security.Key;
// import java.util.Date;

// public class JwtUtil {

//     // These fields are set via ReflectionTestUtils in tests
//     private String secret;
//     private Long jwtExpirationMs;

//     // Generate JWT token
//     public String generateToken(String username, String role, Long userId, String email) {

//         Key key = Keys.hmacShaKeyFor(secret.getBytes());

//         return Jwts.builder()
//                 .setSubject(username)
//                 .claim("role", role)
//                 .claim("userId", userId)
//                 .claim("email", email)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
//                 .signWith(key, SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     // Validate token and return claims
//     public io.jsonwebtoken.Jws<Claims> validateAndGetClaims(String token)
//             throws JwtException {

//         Key key = Keys.hmacShaKeyFor(secret.getBytes());

//         return Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token);
//     }
// }
