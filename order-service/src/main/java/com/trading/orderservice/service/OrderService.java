package com.trading.orderservice.service;

import com.trading.orderservice.dto.OrderRequest;
import com.trading.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder( OrderRequest request);

    Order getOrder(String orderId);

    List<Order> getMyOrders(String userId);
}
