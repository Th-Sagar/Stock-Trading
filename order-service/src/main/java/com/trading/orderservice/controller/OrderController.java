package com.trading.orderservice.controller;

import com.trading.orderservice.dto.OrderRequest;
import com.trading.orderservice.entity.Order;
import com.trading.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Orders",description = "Orders operations")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Place an Order")
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestHeader("X-User-Id") String userId, @Valid @RequestBody OrderRequest request){
        request.setUserId(userId);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.placeOrder(request));
    }

    @Operation(summary = "Get order by orderId")
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId, @RequestHeader("X-User-Id") String userId){
        Order order = orderService.getOrder(orderId);

        if(!order.getUserId().equals(userId)){
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(order);
    }

    @Operation(summary = "Login user orders detail")
    @GetMapping("/my-orders")
    public ResponseEntity<List<Order>> getMyOrders(
            @RequestHeader("X-User-Id") String userId){
        return ResponseEntity.ok(orderService.getMyOrders(userId));
    }


}
