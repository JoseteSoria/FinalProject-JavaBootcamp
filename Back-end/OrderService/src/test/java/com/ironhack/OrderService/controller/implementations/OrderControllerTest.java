package com.ironhack.OrderService.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.OrderService.classes.Money;
import com.ironhack.OrderService.model.Order;
import com.ironhack.OrderService.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

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

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        order = new Order();
        order.setId(1l);
        Order order2 = new Order(new Money(new BigDecimal("8.12")));

        List<Order> orders = Arrays.asList(order, order2);
        when(orderService.findAll()).thenReturn(orders);
        when(orderService.findById(order.getId())).thenReturn(order);
        when(orderService.findLastOrder()).thenReturn(order2);
        doAnswer(i -> {
            return null;
        }).when(orderService).store(order);
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/orders/" + order.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void findLastOrder() throws Exception {
        mockMvc.perform(get("/orders/last"))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/orders").content(objectMapper.writeValueAsString(order))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}