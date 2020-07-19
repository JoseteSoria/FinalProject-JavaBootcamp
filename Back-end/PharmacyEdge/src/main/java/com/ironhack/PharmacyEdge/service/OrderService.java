package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.client.OrderClient;
import com.ironhack.PharmacyEdge.exceptions.OrderServiceDownException;
import com.ironhack.PharmacyEdge.model.order.MedicineOrdered;
import com.ironhack.PharmacyEdge.model.order.Order;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderService.class);

    @Autowired
    OrderClient orderClient;

    @HystrixCommand(fallbackMethod = "errorFindAllOrders")
    public List<Order> findAllOrders() {
        LOGGER.info("GET request to retrieve all orders");
        return orderClient.findAllOrders();
    }

    public List<Order> errorFindAllOrders() {
        LOGGER.error("Controlled exception - fail in GET request to retrieve all orders. ");
        throw new OrderServiceDownException(" Order Service Down. Method findAllOrders. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindOrderById")
    public Order findOrderById(Long id) {
        LOGGER.info("GET request to retrieve order by Id. Id: " + id);
        return orderClient.findOrderById(id);
    }

    public Order errorFindOrderById(Long id) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve order by Id. Id: " + id);
        throw new OrderServiceDownException(" Order Service Down. Method findOrderById. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindLastOrder")
    public Order findLastOrder() {
        LOGGER.info("GET request to retrieve last order");
        return orderClient.findLastOrder();
    }

    public Order errorFindLastOrder() {
        LOGGER.error("Controlled exception - fail in GET request to retrieve last order");
        throw new OrderServiceDownException(" Order Service Down. Method findLastOrder. ");
    }

    @HystrixCommand(fallbackMethod = "errorCreateOrder")
    public Order createOrder(Order order) {
        LOGGER.info("POST request to create a new order");
        return orderClient.createOrder(order);
    }

    public Order errorCreateOrder(Order order) {
        LOGGER.error("Controlled exception - fail in POST request to create a new order");
        throw new OrderServiceDownException(" Order Service Down. Method createOrder. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindAllMedicinesOrdered")
    public List<MedicineOrdered> findAllMedicinesOrdered() {
        LOGGER.info("GET request to retrieve all medicines ordered");
        return orderClient.findAllMedicinesOrdered();
    }

    public List<MedicineOrdered> errorFindAllMedicinesOrdered() {
        LOGGER.error("Controlled exception - fail in GET request to retrieve all medicines ordered");
        throw new OrderServiceDownException(" Order Service Down. Method findAllMedicinesOrdered. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindAllMedicineOrderedById")
    public MedicineOrdered findMedicineOrderedById(Long id) {
        LOGGER.info("GET request to retrieve medicine-ordered by id. Id: " + id);
        return orderClient.findMedicineOrderedById(id);
    }

    public MedicineOrdered errorFindAllMedicineOrderedById(Long id) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve medicine-ordered by id. Id: " + id);
        throw new OrderServiceDownException(" Order Service Down. Method findMedicineOrderedById. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindAllMedicineOrderedByOrderId")
    public List<MedicineOrdered> findMedicineOrderedByOrderId(Long id) {
        LOGGER.info("GET request to retrieve medicine-ordered by orderId. OrderId: " + id);
        return orderClient.findMedicineOrderedByOrderId(id);
    }

    public List<MedicineOrdered> errorFindAllMedicineOrderedByOrderId(Long id) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve medicine-ordered by orderId. OrderId: " + id);
        throw new OrderServiceDownException(" Order Service Down. Method findMedicineOrderedByOrderId. ");
    }

    @HystrixCommand(fallbackMethod = "errorCreateMedicineOrdered")
    public List<MedicineOrdered> createMedicineOrdered(List<MedicineOrdered> medicines) {
        LOGGER.info("POST request to add new medicines ordered");
        return orderClient.createMedicineOrdered(medicines);
    }

    public List<MedicineOrdered> errorCreateMedicineOrdered(List<MedicineOrdered> medicines) {
        LOGGER.error("Controlled exception - fail in POST request to add new medicines ordered");
        throw new OrderServiceDownException(" Order Service Down. Method createMedicineOrdered. ");
    }
}
