package com.ironhack.OrderService.controller.interfaces;

import com.ironhack.OrderService.model.Order;

import java.util.List;

public interface IOrderController {
    public List<Order> findAllOrders();

    public Order findOrderById(Long id);

    public Order findLastOrder();

    public Order createOrder(Order order);
}
