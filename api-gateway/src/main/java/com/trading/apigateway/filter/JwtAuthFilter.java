package com.trading.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.List;

@Slf4j
@Component
public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config> {

    private static final List<String> PUBLIC_PATHS = List.of(
            "/api/v1/users/register",
            "/api/v1/users/login",
            "/v3/api-docs",
            "/swagger-ui",
            "/webjars",
            "/actuator/health",
            "/ws"
    );

    private boolean isPublicEndpoint(String path) {
        return PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    }

    @Value("${jwt.secret}")
    private String secret;

    public JwtAuthFilter(){
        super(Config.class);
    }


    @Override
    public GatewayFilter apply(JwtAuthFilter.Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getPath().toString();
            
            //skip jwt for public endpoints

            if(isPublicEndpoint(path)){
                log.debug("Public endpoint - skipping JWT: {}",path);
                return chain.filter(exchange);
            }

            if(!request.getHeaders().containsHeader("Authorization")){
                log.warn("Missing Authorization header: {}", path);
                return unAuthorizedResponse(exchange,"Authorization header missing");
            }

            String authHeader = request.getHeaders().getFirst("Authorization");

            if(authHeader ==null || !authHeader.startsWith("Bearer ")){
                return unAuthorizedResponse(exchange,"Invalid Authorization format");
            }

            String token = authHeader.substring(7);

            try {

                Claims claims = extractClaims(token);
                String userId = claims.get("userId",String.class);
                String email = claims.getSubject();

                log.debug("UserId: {} email: {}", userId, email);

                ServerHttpRequest modifiedRequest = request
                        .mutate()
                        .header("X-User-Id",userId)
                        .build();

                return chain.filter(exchange
                        .mutate()
                        .request(modifiedRequest)
                        .build());

            } catch (Exception e) {

                log.warn("Invalid JWT token: {} ",e.getMessage());
                return unAuthorizedResponse(exchange,"Invalid or expired token");
            }

        };
    }

    private Claims extractClaims(String token){
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Mono<Void> unAuthorizedResponse(ServerWebExchange exchange, String message) {

        log.warn("Unauthorized: {}", message);

        exchange.getResponse()
                .setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    public static class Config{

    }
}
