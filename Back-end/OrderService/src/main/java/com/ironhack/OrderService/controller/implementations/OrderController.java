package com.ironhack.OrderService.controller.implementations;

import com.ironhack.OrderService.controller.interfaces.IOrderController;
import com.ironhack.OrderService.model.Order;
import com.ironhack.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController implements IOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order findOrderById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/orders/last")
    @ResponseStatus(HttpStatus.OK)
    public Order findLastOrder() {
        return orderService.findLastOrder();
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody @Valid Order order) {
        return orderService.store(order);
    }
}
