package com.ironhack.PharmacyEdge.controller.implementations;

import com.ironhack.PharmacyEdge.controller.interfaces.IOrderController;
import com.ironhack.PharmacyEdge.model.order.MedicineOrdered;
import com.ironhack.PharmacyEdge.model.order.Order;
import com.ironhack.PharmacyEdge.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController implements IOrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> findAllOrders(){
        return orderService.findAllOrders();
    }

    @GetMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order findOrderById(@PathVariable Long id){
        return orderService.findOrderById(id);
    }

    @GetMapping("/orders/last")
    @ResponseStatus(HttpStatus.OK)
    public Order findLastOrder(){
        return orderService.findLastOrder();
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody @Valid Order order){
        return orderService.createOrder(order);
    }

    @GetMapping("/medicines-ordered")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicineOrdered> findAllMedicinesOrdered(){
        return orderService.findAllMedicinesOrdered();
    }

    @GetMapping("/medicines-ordered/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MedicineOrdered findMedicineOrderedById(@PathVariable Long id){
        return orderService.findMedicineOrderedById(id);
    }

    @GetMapping("/medicines-ordered/order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicineOrdered> findMedicineOrderedByOrderId(@PathVariable Long id){
        return orderService.findMedicineOrderedByOrderId(id);
    }

    @PostMapping("/medicines-ordered")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MedicineOrdered> createMedicineOrdered(@RequestBody @Valid List<MedicineOrdered> medicines){
        return orderService.createMedicineOrdered(medicines);
    }
}
