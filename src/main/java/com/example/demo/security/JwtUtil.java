

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

package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ✅ DEFAULT VALUES (prevents Swagger 500 error)
    private String secret = "0123456789ABCDEF0123456789ABCDEF";
    private Long jwtExpirationMs = 3600000L; // 1 hour

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username, String role, Long userId, String email) {

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("userId", userId)
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> validateAndGetClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }

    // ✅ Needed for JwtAuthenticationFilter
    public String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}

