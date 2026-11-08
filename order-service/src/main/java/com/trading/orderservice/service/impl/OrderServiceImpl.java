package com.trading.orderservice.service.impl;

import com.trading.orderservice.dto.OrderRequest;
import com.trading.orderservice.entity.Order;
import com.trading.orderservice.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order placeOrder(OrderRequest request) {
        return null;
    }

    @Override
    public Order getOrder(String orderId) {
        return null;
    }

    @Override
    public List<Order> getMyOrders(String userId) {
        return List.of();
    }
}
