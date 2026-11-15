package com.trading.userservice.controller;

import com.trading.userservice.dto.AuthResponse;
import com.trading.userservice.dto.LoginRequest;
import com.trading.userservice.dto.RegisterRequest;
import com.trading.userservice.dto.UserResponse;
import com.trading.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users",description = "Users operations")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Register User")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(registerRequest));
    }

    @Operation(summary = "Login User")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }

    @Operation(summary = "Login User details")
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyProfile(@RequestHeader("X-User-Id") String userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Operation(summary = "Add funds")
    @PostMapping("/{userId}/funds/add")
    public ResponseEntity<UserResponse> addFunds(@PathVariable String userId, @RequestParam BigDecimal amount){
        return ResponseEntity.ok(userService.addFunds(userId,amount));
    }

    @Operation(summary = "Funds deduction")
    @PostMapping("/{userId}/funds/deduct")
    public ResponseEntity<UserResponse> deductFunds(@PathVariable String userId, @RequestParam BigDecimal amount){
        return ResponseEntity.ok(userService.deductFunds(userId,amount));
    }

    @Operation(summary = "Funds credit")
    @PostMapping("/{userId}/funds/credit")
    public ResponseEntity<UserResponse> creditFunds(@PathVariable String userId, @RequestParam BigDecimal amount){
        return ResponseEntity.ok(userService.creditFunds(userId,amount));
    }

}
