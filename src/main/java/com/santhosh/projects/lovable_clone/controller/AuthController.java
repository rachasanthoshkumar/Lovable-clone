package com.santhosh.projects.lovable_clone.controller;


import com.santhosh.projects.lovable_clone.dto.auth.AuthResponse;
import com.santhosh.projects.lovable_clone.dto.auth.LoginRequest;
import com.santhosh.projects.lovable_clone.dto.auth.SignupRequest;
import com.santhosh.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.santhosh.projects.lovable_clone.service.AuthService;
import com.santhosh.projects.lovable_clone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;


    @PostMapping(path = "/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest request)
    {
        return  ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile()
    {
        Long userId = 1L;
        return ResponseEntity.ok(userService.getProfile(userId));
    }
}
