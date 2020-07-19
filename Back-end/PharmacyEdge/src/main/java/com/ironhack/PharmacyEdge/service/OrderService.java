package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.client.OrderClient;
import com.ironhack.PharmacyEdge.model.order.MedicineOrdered;
import com.ironhack.PharmacyEdge.model.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderClient orderClient;

    public List<Order> findAllOrders() {
        return orderClient.findAllOrders();
    }

    public Order findOrderById(Long id) {
        return orderClient.findOrderById(id);
    }

    public Order findLastOrder() {
        return orderClient.findLastOrder();
    }

    public Order createOrder(Order order) {
        return orderClient.createOrder(order);
    }

    public List<MedicineOrdered> findAllMedicinesOrdered() {
        return orderClient.findAllMedicinesOrdered();
    }

    public MedicineOrdered findMedicineOrderedById(Long id) {
        return orderClient.findMedicineOrderedById(id);
    }

    public List<MedicineOrdered> findMedicineOrderedByOrderId(Long id) {
        return orderClient.findMedicineOrderedByOrderId(id);
    }

    public List<MedicineOrdered> createMedicineOrdered(List<MedicineOrdered> medicines) {
        return orderClient.createMedicineOrdered(medicines);
    }
}
