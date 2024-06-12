package com.vd.restaurant.restaurant.commission.calculator.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.vd.restaurant.restaurant.commission.calculator.entities.MenuItem;
import com.vd.restaurant.restaurant.commission.calculator.entities.Order;
import com.vd.restaurant.restaurant.commission.calculator.entities.OrderItem;
import com.vd.restaurant.restaurant.commission.calculator.repositories.OrderRepository;

@SpringBootTest
public class RevenueServiceTest {

    @InjectMocks
    private RevenueService revenueService;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateTotalRevenue() {
        MenuItem menuItem1 = new MenuItem();
        menuItem1.setPrice(10.5);
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setMenuItem(menuItem1);
        orderItem1.setQuantity(2);

        MenuItem menuItem2 = new MenuItem();
        menuItem2.setPrice(8.0);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setMenuItem(menuItem2);
        orderItem2.setQuantity(3);

        Order order = new Order();
        order.setItems(Arrays.asList(orderItem1, orderItem2));

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order));

        double result = revenueService.calculateTotalRevenue();
        assertEquals(45.0, result);
    }

    @Test
    public void testCalculateCommissionedRevenue() {
        RevenueService spyService = spy(revenueService);
        doReturn(100.0).when(spyService).calculateTotalRevenue();

        double result = spyService.calculateCommissionedRevenue();
        assertEquals(12.0, result);
    }
}