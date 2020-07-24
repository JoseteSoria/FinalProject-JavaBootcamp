package com.ironhack.PharmacyEdge.controller.interfaces;

import com.ironhack.PharmacyEdge.model.order.MedicineOrdered;
import com.ironhack.PharmacyEdge.model.order.Order;

import java.util.List;

public interface IOrderController {
    public List<Order> findAllOrders();

    public Order findOrderById(Long id);

    public Order findLastOrder();

    public Order createOrder(Order order);

    public List<MedicineOrdered> findAllMedicinesOrdered();

    public MedicineOrdered findMedicineOrderedById(Long id);

    public List<MedicineOrdered> findMedicineOrderedByOrderId(Long id);

    public List<MedicineOrdered> createMedicineOrdered(List<MedicineOrdered> medicines);
}
