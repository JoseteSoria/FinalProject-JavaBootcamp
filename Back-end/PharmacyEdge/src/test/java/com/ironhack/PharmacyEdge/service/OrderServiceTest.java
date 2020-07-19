package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.client.OrderClient;
import com.ironhack.PharmacyEdge.exceptions.OrderServiceDownException;
import com.ironhack.PharmacyEdge.model.order.MedicineOrdered;
import com.ironhack.PharmacyEdge.model.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderClient orderClient;

    private Order order, order2;
    private MedicineOrdered medicineOrdered, medicineOrdered2, medicineOrdered3;


    @BeforeEach
    void setUp() {
        order = new Order();
        order.setId(1l);
        order2 = new Order();
        List<Order> orders = Arrays.asList(order2, order);
        when(orderClient.findAllOrders()).thenReturn(orders);
        when(orderClient.findOrderById(order.getId())).thenReturn(order);
        when(orderClient.findLastOrder()).thenReturn(order2);
        doAnswer(i -> {
            return null;
        }).when(orderClient).createOrder(order);

        medicineOrdered = new MedicineOrdered(1l, 1l, 10);
        medicineOrdered.setId(1l);
        medicineOrdered2 = new MedicineOrdered(1l, 2l, 5);
        List<MedicineOrdered> medicines = Arrays.asList(medicineOrdered, medicineOrdered2);
        when(orderClient.findAllMedicinesOrdered()).thenReturn(medicines);
        when(orderClient.findMedicineOrderedById(medicineOrdered.getId())).thenReturn(medicineOrdered);
        when(orderClient.findMedicineOrderedByOrderId(1l)).thenReturn(medicines);
        medicineOrdered3 = new MedicineOrdered(1l, 3l, 5);
        doAnswer(i -> {
            return null;
        }).when(orderClient).createMedicineOrdered(Collections.singletonList(medicineOrdered3));
    }

    @Test
    @DisplayName("Unit test - retrieval of all orders")
    void findAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        assertEquals(2, orders.size());
    }

    @Test
    void errorFindAllOrders() {
        assertThrows(OrderServiceDownException.class, () -> orderService.errorFindAllOrders());
    }

    @Test
    @DisplayName("Unit test - retrieval of a order with specific id")
    void findOrderById() {
        assertEquals(order, orderService.findOrderById(1l));
    }

    @Test
    void errorFindOrderById() {
        assertThrows(OrderServiceDownException.class, () -> orderService.errorFindOrderById(null));
    }

    @Test
    @DisplayName("Unit test - retrieval the last order")
    void findLastOrder() {
        assertEquals(order2, orderService.findLastOrder());
    }

    @Test
    void errorFindLastOrder() {
        assertThrows(OrderServiceDownException.class, () -> orderService.errorFindLastOrder());
    }

    @Test
    @DisplayName("Unit test - create a new order")
    void createOrder() {
        orderService.createOrder(order);
    }

    @Test
    void errorCreateOrder() {
        assertThrows(OrderServiceDownException.class, () -> orderService.errorCreateOrder(null));
    }

    @Test
    @DisplayName("Unit test - retrieval of all medicines-ordered")
    void findAllMedicinesOrdered() {
        List<MedicineOrdered> medicineOrderedList = orderService.findAllMedicinesOrdered();
        assertEquals(2, medicineOrderedList.size());
    }

    @Test
    void errorFindAllMedicinesOrdered() {
        assertThrows(OrderServiceDownException.class, () -> orderService.errorFindAllMedicinesOrdered());
    }

    @Test
    @DisplayName("Unit test - retrieval of a medicine-ordered with specific id")
    void findMedicineOrderedById() {
        assertEquals(medicineOrdered, orderService.findMedicineOrderedById(medicineOrdered.getId()));
    }

    @Test
    void errorFindMedicineOrderedById() {
        assertThrows(OrderServiceDownException.class, () -> orderService.errorFindAllMedicineOrderedById(null));
    }

    @Test
    @DisplayName("Unit test - retrieval of a medicine-ordered with specific orderId")
    void findMedicineOrderedByOrderId() {
        List<MedicineOrdered> medicineOrderedList = orderService.findMedicineOrderedByOrderId(medicineOrdered.getOrderId());
        assertEquals(2, medicineOrderedList.size());
    }

    @Test
    void errorFindMedicineOrderedByOrderId() {
        assertThrows(OrderServiceDownException.class, () -> orderService.errorFindAllMedicineOrderedByOrderId(null));
    }

    @Test
    @DisplayName("Unit test - add a new medicine-ordered")
    void createMedicineOrdered() {
        orderService.createMedicineOrdered(Collections.singletonList(medicineOrdered3));
    }

    @Test
    void errorCreateMedicineOrdered() {
        assertThrows(OrderServiceDownException.class, () -> orderService.errorCreateOrder(null));
    }
}