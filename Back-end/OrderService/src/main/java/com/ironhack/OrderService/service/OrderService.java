package com.ironhack.OrderService.service;

import com.ironhack.OrderService.exceptions.ResourceNotFoundException;
import com.ironhack.OrderService.model.Order;
import com.ironhack.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAllByOrderByDateDesc();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order nos found with that id")
        );
    }

    public Order findLastOrder() {
        return orderRepository.findLastOrder();
    }

    public Order store(Order order) {
        return orderRepository.save(order);
    }
}
