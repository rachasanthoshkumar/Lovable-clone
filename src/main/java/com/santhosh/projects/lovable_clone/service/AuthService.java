package com.santhosh.projects.lovable_clone.service;

import com.santhosh.projects.lovable_clone.dto.auth.AuthResponse;
import com.santhosh.projects.lovable_clone.dto.auth.LoginRequest;
import com.santhosh.projects.lovable_clone.dto.auth.SignupRequest;


public interface AuthService {
     AuthResponse signup(SignupRequest request);

     AuthResponse login(LoginRequest request);
}
