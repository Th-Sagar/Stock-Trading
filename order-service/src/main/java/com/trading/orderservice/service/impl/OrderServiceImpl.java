package com.trading.orderservice.service.impl;

import com.trading.orderservice.client.MarketDataClient;
import com.trading.orderservice.client.UserServiceClient;
import com.trading.orderservice.dto.OrderRequest;
import com.trading.orderservice.entity.Order;
import com.trading.orderservice.entity.OrderStatus;
import com.trading.orderservice.entity.OrderType;
import com.trading.orderservice.repository.OrderRepository;
import com.trading.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MarketDataClient marketDataClient;
    private final UserServiceClient userServiceClient;
    private final KafkaTemplate<String,Object> kafkaTemplate;

    private static final String ORDER_EXECUTED_TOPIC="order.executed";
    private static final String ORDER_FAILED_TOPIC="order.failed";

    @Override
    public Order placeOrder(OrderRequest request) {
        log.info("Placing {} order: {} x {} for users: {}",
                request.getType(), request.getQuantity(), request.getSymbol(),request.getUserId());

        Map<String,Object> priceData = marketDataClient.getStockPrice(request.getSymbol());
        BigDecimal currentPrice = new BigDecimal(priceData.get("price").toString());

        BigDecimal totalAmount = currentPrice.multiply(BigDecimal.valueOf(request.getQuantity()));

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setSymbol(request.getSymbol());
        order.setType(request.getType());
        order.setQuantity(request.getQuantity());
        order.setPrice(currentPrice);
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.PENDING);

        Order savedOrder = orderRepository.save(order);
        log.info("Order created: {}", savedOrder.getId());

        try {

            if(request.getType() == OrderType.BUY){
                userServiceClient.deductFunds(request.getUserId(),totalAmount);
            }else {
                userServiceClient.creditFunds(request.getUserId(),totalAmount);
            }

            savedOrder.setStatus(OrderStatus.EXECUTED);
            savedOrder.setExecutedAt(LocalDateTime.now());
            orderRepository.save(savedOrder);

            log.info("Order Executed: {} {} x {} at {}", request.getType(), request.getQuantity(), request.getSymbol(), currentPrice);

            publishOrderEvent(ORDER_EXECUTED_TOPIC,savedOrder,null);

        }catch (Exception e){
            savedOrder.setStatus(OrderStatus.FAILED);
            savedOrder.setFailureReason(e.getMessage());
            orderRepository.save(savedOrder);
            log.info("Order Failed: {}  reason {}", savedOrder.getId(),e.getMessage());
            publishOrderEvent(ORDER_FAILED_TOPIC,savedOrder,e.getMessage());
        }
        return savedOrder;
    }

    @Override
    public Order getOrder(String orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(()-> new RuntimeException("Order not found: "+ orderId));
    }

    @Override
    public List<Order> getMyOrders(String userId) {
        return orderRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    private void publishOrderEvent(String topic, Order order, String reason) {

        Map<String,Object> orderEvent = new HashMap<>();
        orderEvent.put("orderId",order.getId());
        orderEvent.put("userId",order.getUserId());
        orderEvent.put("symbol",order.getSymbol());
        orderEvent.put("type",order.getType().name());
        orderEvent.put("quantity",order.getQuantity());
        orderEvent.put("price",order.getPrice());
        orderEvent.put("totalAmount",order.getTotalAmount());
        orderEvent.put("status",order.getStatus().name());
        if(reason !=null) orderEvent.put("reason",reason);

        kafkaTemplate.send(topic, order.getId(), orderEvent);
        log.info("Event published: {} for order: {} ", topic,order.getId());
    }
}
