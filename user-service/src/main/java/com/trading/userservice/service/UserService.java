package com.trading.userservice.service;

import com.trading.userservice.dto.AuthResponse;
import com.trading.userservice.dto.LoginRequest;
import com.trading.userservice.dto.RegisterRequest;
import com.trading.userservice.dto.UserResponse;
import jakarta.validation.Valid;

import java.math.BigDecimal;

public interface UserService {
    AuthResponse register(@Valid RegisterRequest registerRequest);

    AuthResponse login(@Valid LoginRequest request);

    UserResponse getUserById(String userId);

    UserResponse addFunds(String userId, BigDecimal amount);

    UserResponse deductFunds(String userId, BigDecimal amount);

    UserResponse creditFunds(String userId, BigDecimal amount);
}
