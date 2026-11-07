package com.trading.userservice.service.impl;

import com.trading.userservice.dto.AuthResponse;
import com.trading.userservice.dto.LoginRequest;
import com.trading.userservice.dto.RegisterRequest;
import com.trading.userservice.dto.UserResponse;
import com.trading.userservice.entity.User;
import com.trading.userservice.repository.UserRepository;
import com.trading.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private static final String USER_REGISTERED_TOPIC="user.registered";

    @Override
    public AuthResponse register(RegisterRequest request) {

        log.info("registering user: {}", request.getEmail());

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already registered " + request.getEmail());
        }

        User user = User
                .builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .walletBalance(
                        request.getInitialDeposit() !=null ?
                                request.getInitialDeposit(): BigDecimal.valueOf(10000)
                )
                .build();

        User savedUser = userRepository.save(user);
        log.info("User registered: {}", savedUser.getId());


        //publish user.registered event

        Map<String,Object> userRegisteredEvent = new HashMap<>();
        userRegisteredEvent.put("userId",savedUser.getId());
        userRegisteredEvent.put("email",savedUser.getEmail());
        userRegisteredEvent.put("firstName",savedUser.getFirstName());
        userRegisteredEvent.put("lastName",savedUser.getLastName());
        userRegisteredEvent.put("walletBalance",savedUser.getWalletBalance());
        kafkaTemplate.send(USER_REGISTERED_TOPIC, savedUser.getId(),userRegisteredEvent);
        String token = generateAccessToken(savedUser.getId(),savedUser.getEmail());
        String refreshToken = generateRefreshToken(savedUser.getId());
        return buildAuthResponse(savedUser,token,refreshToken);
    }

    private AuthResponse buildAuthResponse(User savedUser, String token, String refreshToken) {
        return AuthResponse.
                builder()
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .walletBalance(savedUser.getWalletBalance())
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        log.info("Login attempt: {}", request.getEmail());
        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("User not found with an email: " + request.getEmail()));
        if(passwordEncoder.matches(request.getPassword() ,user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }
        log.info("Login successful: {}", request.getEmail());

        String token = generateAccessToken(user.getId(),user.getEmail());
        String refreshToken = generateRefreshToken(user.getId());
        return buildAuthResponse(user,token,refreshToken);
    }

//    Called by other services via feign (internal services)

    @Override
    public UserResponse getUserById(String userId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found with an userId: " + userId));
        return mapToResponse(user);
    }

//    add funds to wallet

    @Override
    public UserResponse addFunds(String userId, BigDecimal amount) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found with an userId: " + userId));

        user.setWalletBalance(user.getWalletBalance().add(amount));
        User savedUser = userRepository.save(user);
        log.info("Funds added: {} to user: {}",amount,userId);
        return mapToResponse(savedUser);
    }

//     deduct funds from wallet - called on buy order

    @Override
    public UserResponse deductFunds(String userId, BigDecimal amount) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found with an userId: " + userId));

        if(user.getWalletBalance().compareTo(amount) <0){
            throw new RuntimeException("Insufficient wallet Balance");
        }

        user.setWalletBalance(user.getWalletBalance().subtract(amount));
        User savedUser = userRepository.save(user);
        log.info("Funds deducted: {} to user: {}",amount,userId);
        return mapToResponse(savedUser);
    }

//    credit funds to wallet - called on sell order

    @Override
    public UserResponse creditFunds(String userId, BigDecimal amount) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found with an userId: " + userId));

        user.setWalletBalance(user.getWalletBalance().add(amount));
        User savedUser = userRepository.save(user);
        log.info("Funds credited: {} to user: {}",amount,userId);
        return mapToResponse(savedUser);
    }


    private UserResponse mapToResponse(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .walletBalance(user.getWalletBalance())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .build();
    }

    private String generateRefreshToken(String id) {
        return "";
    }

    private String generateAccessToken(String id, String email) {
        return "";
    }
}
