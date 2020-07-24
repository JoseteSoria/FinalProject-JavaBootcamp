package com.ironhack.OrderService.service;

import com.ironhack.OrderService.exceptions.ResourceNotFoundException;
import com.ironhack.OrderService.model.Order;
import com.ironhack.OrderService.repository.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        LOGGER.info("GET request to retrieve all orders");
        return orderRepository.findAllByOrderByDateDesc();
    }

    public Order findById(Long id) {
        LOGGER.info("GET request to retrieve order by id. Id: " + id);
        return orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order nos found with that id")
        );
    }

    public Order findLastOrder() {
        LOGGER.info("GET request to retrieve last order.");
        return orderRepository.findLastOrder();
    }

    public Order store(Order order) {
        LOGGER.info("POST request to store a new order.");
        return orderRepository.save(order);
    }
}
