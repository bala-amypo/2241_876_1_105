
package com.example.demo.util;

public class JwtUtil {

    private JwtUtil() {
        // Utility class - prevent instantiation
    }

    public static boolean isTokenPresent(String token) {
        return token != null && !token.trim().isEmpty();
    }

    public static boolean isBearerToken(String header) {
        return header != null && header.startsWith("Bearer ");
    }
}
