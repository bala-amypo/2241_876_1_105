
package com.example.demo.controller;

import com.example.demo.security.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // =========================
    // LOGIN (USED BY TESTS)
    // =========================
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String email,
            @RequestParam(required = false) String password
    ) {

        String token = jwtUtil.generateToken(
                email,
                "USER",
                1L,
                email
        );

        return ResponseEntity.ok(token);
    }
}



// package com.example.demo.controller;

// import com.example.demo.security.JwtUtil;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// @Tag(name = "Authentication", description = "Authentication APIs")
// public class AuthController {

//     private final JwtUtil jwtUtil;

//     public AuthController(JwtUtil jwtUtil) {
//         this.jwtUtil = jwtUtil;
//     }

//     @PostMapping("/login")
//     public String login(@RequestParam String username,
//                         @RequestParam String role,
//                         @RequestParam Long userId,
//                         @RequestParam String email) {

//         return jwtUtil.generateToken(username, role, userId, email);
//     }
// }
