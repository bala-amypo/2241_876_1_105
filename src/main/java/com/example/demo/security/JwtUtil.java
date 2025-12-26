package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // SAFE DEFAULTS (Swagger-safe, Test-safe)
    private static final String DEFAULT_SECRET =
            "thisIsASecretKeyForJwtSigningThatIsAtLeast32BytesLong";
    private static final long DEFAULT_EXPIRATION = 86400000; // 1 day

    private Key key;
    private long jwtExpirationMs;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(DEFAULT_SECRET.getBytes());
        this.jwtExpirationMs = DEFAULT_EXPIRATION;
    }

    public String generateToken(String username, String role, Long userId, String email) {

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("userId", userId)
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
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


