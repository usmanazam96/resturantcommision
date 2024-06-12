package com.vd.restaurant.restaurant_commission_calculator.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vd.restaurant.restaurant_commission_calculator.entities.Order;
import com.vd.restaurant.restaurant_commission_calculator.entities.OrderItem;
import com.vd.restaurant.restaurant_commission_calculator.exceptions.OrderNotFoundException;
import com.vd.restaurant.restaurant_commission_calculator.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order with id " + id + " not found");
        }
        orderRepository.deleteById(id);
    }

    public Order addOrderItem(Long orderId, OrderItem orderItem) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));
        order.getItems().add(orderItem);
        return orderRepository.save(order);
    }

    public Order removeOrderItem(Long orderId, Long orderItemId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));
        order.getItems().removeIf(item -> item.getId().equals(orderItemId));
        return orderRepository.save(order);
    }
}
