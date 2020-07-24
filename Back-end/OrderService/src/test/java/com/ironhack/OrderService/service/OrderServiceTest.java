package com.ironhack.OrderService.service;

import com.ironhack.OrderService.exceptions.ResourceNotFoundException;
import com.ironhack.OrderService.model.Order;
import com.ironhack.OrderService.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    private Order order, order2;

    @BeforeEach
    void setUp() {
        order = new Order();
        order.setId(1l);
        order2 = new Order();

        List<Order> orders = Arrays.asList(order2, order);
        when(orderRepository.findAllByOrderByDateDesc()).thenReturn(orders);
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        when(orderRepository.findLastOrder()).thenReturn(order2);
        doAnswer(i -> {
            return null;
        }).when(orderRepository).save(order);
    }

    @Test
    @DisplayName("Unit test - retrieval of all orders")
    void findAll() {
        List<Order> orders = orderService.findAll();
        assertEquals(2, orders.size());
    }

    @Test
    @DisplayName("Unit test - retrieval of a order with specific id")
    void findById() {
        assertEquals(order, orderService.findById(1l));
    }

    @Test
    @DisplayName("Unit test - not found a medicine with specific id")
    void findById_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> orderService.findById(0l));
    }

    @Test
    @DisplayName("Unit test - retrieval the last order")
    void findLastOrder() {
        assertEquals(order2, orderService.findLastOrder());
    }

    @Test
    @DisplayName("Unit test - create a new order")
    void create() {
        orderService.store(order);
    }
}