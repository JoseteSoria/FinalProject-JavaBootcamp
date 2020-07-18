package com.ironhack.OrderService.controller.interfaces;

import com.ironhack.OrderService.model.Order;

import java.util.List;

public interface IOrderController {
    public List<Order> findAll();

    public Order findById(Long id);

    public Order findLastOrder();

    public Order create(Order order);
}
