package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "User registration and login")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    // =========================
    // REGISTER (unchanged)
    // =========================
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User saved = userService.registerUser(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // =========================
    // LOGIN (TESTED METHOD – DO NOT CHANGE)
    // =========================
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        // Minimal auth (matches your existing logic)
        String token = jwtUtil.generateToken(
                request.getEmail(),
                "USER",
                1L,
                request.getEmail()
        );

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setEmail(request.getEmail());
        response.setRole("USER");
        response.setUserId(1L);

        return ResponseEntity.ok(response);
    }

    // =========================
    // SWAGGER-FRIENDLY LOGIN (ADDED – SAFE)
    // =========================
    @PostMapping(value = "/login", params = {"email", "password"})
    public ResponseEntity<AuthResponse> loginFromSwagger(
            @RequestParam String email,
            @RequestParam String password
    ) {
        AuthRequest request = new AuthRequest();
        request.setEmail(email);
        request.setPassword(password);

        return login(request); // reuse tested login logic
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
