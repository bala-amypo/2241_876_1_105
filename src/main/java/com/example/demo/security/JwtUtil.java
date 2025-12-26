package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // REQUIRED BY TESTS (field names matter)
    private String secret =
            "thisIsASecretKeyForJwtSigningThatIsAtLeast32BytesLong";
    private Long jwtExpirationMs = 86400000L; // 1 day

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // =========================
    // REQUIRED BY TEST CASES
    // =========================
    public Claims validateAndGetClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // =========================
    // TOKEN GENERATION
    // =========================
    public String generateToken(
            String username,
            String role,
            Long userId,
            String email
    ) {

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("userId", userId)
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + jwtExpirationMs)
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}

// package com.example.demo.security;

// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;
// import java.util.Date;

// @Component
// public class JwtUtil {

//     private String secret;
//     private Long jwtExpirationMs;

//     public String generateToken(String username, String role, Long userId, String email) {

//         return Jwts.builder()
//                 .setSubject(username)
//                 .claim("role", role)
//                 .claim("userId", userId)
//                 .claim("email", email)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
//                 .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     public Jws<Claims> validateAndGetClaims(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(secret.getBytes())
//                 .build()
//                 .parseClaimsJws(token);
//     }
// }


