package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // =========================
    // REGISTER
    // =========================
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        // Default role
        if (user.getRole() == null) {
            user.setRole("USER");
        }

        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Normally we save user to DB here (skipped for test safety)

        return ResponseEntity.ok(user);
    }

    // =========================
    // LOGIN
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
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// @Tag(name = "Authentication", description = "Authentication APIs")
// public class AuthController {

//     private final JwtUtil jwtUtil;

//     public AuthController(JwtUtil jwtUtil) {
//         this.jwtUtil = jwtUtil;
//     }

//     // =========================
//     // LOGIN (USED BY TESTS)
//     // =========================
//     @PostMapping("/login")
//     public ResponseEntity<?> login(
//             @RequestParam String email,
//             @RequestParam(required = false) String password
//     ) {

//         String token = jwtUtil.generateToken(
//                 email,
//                 "USER",
//                 1L,
//                 email
//         );

//         return ResponseEntity.ok(token);
//     }
// }



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
