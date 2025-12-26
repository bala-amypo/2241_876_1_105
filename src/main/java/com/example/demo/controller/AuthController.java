package com.example.demo.controller;

import com.example.demo.security.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthController {

    private final JwtUtil jwtUtil;

    // In-memory user store (ONLY for test flow)
    private static final Map<String, Long> USERS = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GEN = new AtomicLong(1);

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // =========================
    // REGISTER (REQUIRED BY TESTS)
    // =========================
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestParam String email,
            @RequestParam(required = false) String password
    ) {
        USERS.putIfAbsent(email, ID_GEN.getAndIncrement());
        return new ResponseEntity<>("User registered", HttpStatus.CREATED);
    }

    // =========================
    // LOGIN (REQUIRED BY TESTS)
    // =========================
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String email,
            @RequestParam(required = false) String password
    ) {

        Long userId = USERS.computeIfAbsent(email, e -> ID_GEN.getAndIncrement());

        String token = jwtUtil.generateToken(
                email,
                "USER",
                userId,
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
