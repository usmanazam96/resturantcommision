package com.vd.restaurant.restaurant.commission.calculator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vd.restaurant.restaurant.commission.calculator.entities.Order;
import com.vd.restaurant.restaurant.commission.calculator.repositories.OrderRepository;

@Service
public class RevenueService {
	
	@Autowired
    private OrderRepository orderRepository;
	
    public double calculateTotalRevenue() {
        List<Order> orders = orderRepository.findAll();
        double totalRevenue = 0.0;
        for (Order order : orders) {
            totalRevenue += order.getItems().stream()
                .mapToDouble(item -> item.getMenuItem().getPrice() * item.getQuantity())
                .sum();
        }
        return totalRevenue;
    }

    public double calculateCommissionedRevenue() {
        double totalRevenue = calculateTotalRevenue();
        return totalRevenue * 0.12; 
    }

}
