package com.vd.restaurant.restaurant_commission_calculator.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vd.restaurant.restaurant_commission_calculator.entities.Order;
import com.vd.restaurant.restaurant_commission_calculator.entities.OrderItem;
import com.vd.restaurant.restaurant_commission_calculator.exceptions.OrderNotFoundException;
import com.vd.restaurant.restaurant_commission_calculator.repositories.OrderRepository;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order();
        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.createOrder(order);
        assertEquals(order, result);
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orders = Arrays.asList(new Order(), new Order());
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();
        assertEquals(orders, result);
    }

    @Test
    public void testGetOrderById() {
        Order order = new Order();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> result = orderService.getOrderById(1L);
        assertTrue(result.isPresent());
        assertEquals(order, result.get());
    }

    @Test
    public void testDeleteOrder() {
        when(orderRepository.existsById(1L)).thenReturn(true);
        doNothing().when(orderRepository).deleteById(1L);

        assertDoesNotThrow(() -> orderService.deleteOrder(1L));
    }

    @Test
    public void testDeleteOrderNotFound() {
        when(orderRepository.existsById(1L)).thenReturn(false);

        assertThrows(OrderNotFoundException.class, () -> orderService.deleteOrder(1L));
    }

    @Test
    public void testAddOrderItem() {
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.addOrderItem(1L, orderItem);
        assertTrue(result.getItems().contains(orderItem));
    }

    @Test
    public void testRemoveOrderItem() {
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        order.getItems().add(orderItem);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.removeOrderItem(1L, 1L);
        assertFalse(result.getItems().contains(orderItem));
    }
}
