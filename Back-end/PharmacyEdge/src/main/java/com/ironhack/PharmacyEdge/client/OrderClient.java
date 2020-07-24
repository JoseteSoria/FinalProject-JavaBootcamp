package com.ironhack.PharmacyEdge.client;

import com.ironhack.PharmacyEdge.model.order.MedicineOrdered;
import com.ironhack.PharmacyEdge.model.order.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "order-service")
public interface OrderClient {
    @GetMapping("/orders")
    public List<Order> findAllOrders();

    @GetMapping("/orders/{id}")
    public Order findOrderById(@PathVariable Long id);

    @GetMapping("/orders/last")
    public Order findLastOrder();

    @PostMapping("/orders")
    public Order createOrder(@RequestBody @Valid Order order);

    @GetMapping("/medicines-ordered")
    public List<MedicineOrdered> findAllMedicinesOrdered();

    @GetMapping("/medicines-ordered/{id}")
    public MedicineOrdered findMedicineOrderedById(@PathVariable Long id);

    @GetMapping("/medicines-ordered/order/{id}")
    public List<MedicineOrdered> findMedicineOrderedByOrderId(@PathVariable Long id);

    @PostMapping("/medicines-ordered")
    public List<MedicineOrdered> createMedicineOrdered(@RequestBody @Valid List<MedicineOrdered> medicines);
}