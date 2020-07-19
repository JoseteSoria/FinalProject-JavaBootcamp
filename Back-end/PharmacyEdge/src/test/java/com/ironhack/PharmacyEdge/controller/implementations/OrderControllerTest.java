package com.ironhack.PharmacyEdge.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.PharmacyEdge.model.order.MedicineOrdered;
import com.ironhack.PharmacyEdge.model.order.Order;
import com.ironhack.PharmacyEdge.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class OrderControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private OrderService orderService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Order order;
    private MedicineOrdered medicineOrdered, medicineOrdered3;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        order = new Order();
        order.setId(1l);
        Order order2 = new Order();
        List<Order> orders = Arrays.asList(order, order2);
        when(orderService.findAllOrders()).thenReturn(orders);
        when(orderService.findOrderById(order.getId())).thenReturn(order);
        when(orderService.findLastOrder()).thenReturn(order2);
        doAnswer(i -> {
            return null;
        }).when(orderService).createOrder(order);

        medicineOrdered = new MedicineOrdered(1l,1l,10);
        medicineOrdered.setId(1l);
        MedicineOrdered medicineOrdered2 = new MedicineOrdered(1l,2l,5);
        List<MedicineOrdered> medicines = Arrays.asList(medicineOrdered, medicineOrdered2);
        when(orderService.findAllMedicinesOrdered()).thenReturn(medicines);
        when(orderService.findMedicineOrderedById(medicineOrdered.getId())).thenReturn(medicineOrdered);
        when(orderService.findMedicineOrderedByOrderId(1l)).thenReturn(medicines);
        medicineOrdered3 = new MedicineOrdered(1l,3l,5);
        doAnswer(i -> {
            return null;
        }).when(orderService).createMedicineOrdered(Collections.singletonList(medicineOrdered3));
    }

    @Test
    void findAllOrders() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk());
    }

    @Test
    void findOrderById() throws Exception {
        mockMvc.perform(get("/orders/" + order.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void findLastOrder() throws Exception {
        mockMvc.perform(get("/orders/last"))
                .andExpect(status().isOk());
    }

    @Test
    void createOrder() throws Exception {
        mockMvc.perform(post("/orders").content(objectMapper.writeValueAsString(order))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void findAllMedicinesOrdered() throws Exception {
        mockMvc.perform(get("/medicines-ordered"))
                .andExpect(status().isOk());
    }

    @Test
    void findMedicineOrderedById() throws Exception {
        mockMvc.perform(get("/medicines-ordered/" + medicineOrdered.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void findMedicineOrderedByOrderId() throws Exception {
        mockMvc.perform(get("/medicines-ordered/order/" + medicineOrdered.getOrderId()))
                .andExpect(status().isOk());
    }

    @Test
    void createMedicineOrdered() throws Exception {
        mockMvc.perform(post("/medicines-ordered").content(objectMapper.writeValueAsString(Collections.singletonList(medicineOrdered3)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}