package com.trading.portfolioservice.controller;

import com.trading.portfolioservice.service.PortfolioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/portfolio")
@RequiredArgsConstructor
@Tag(name = "Portfolio",description = "Portfolio operations")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Operation(summary = "Get login user portfolio")
    @GetMapping("/me")
    public ResponseEntity<Map<String,Object>> getMyPortfolio(
            @RequestHeader("X-User-Id") String userId
     ){
        return ResponseEntity.ok(portfolioService.getPortfolio(userId));
    }

    @Operation(summary = "Get portfolio by userId")
    @GetMapping("/{userId}")
    public ResponseEntity<Map<String,Object>> getPortfolio(@PathVariable String userId){
        return ResponseEntity.ok(portfolioService.getPortfolio(userId));
    }
}
