package com.ironhack.SellService.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.SellService.classes.Money;
import com.ironhack.SellService.model.Sales;
import com.ironhack.SellService.service.SalesService;
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
class SalesControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private SalesService salesService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Sales sales;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        sales = new Sales(1, 1, new Money(new BigDecimal("12.5")));
        sales.setId(1l);
        Sales sales2 = new Sales(1, 1, new Money(new BigDecimal("10")));

        List<Sales> salesList = Arrays.asList(sales, sales2);
        when(salesService.findAll()).thenReturn(salesList);
        when(salesService.findById(sales.getId())).thenReturn(sales);
        when(salesService.findLastSale()).thenReturn(sales2);
        doAnswer(i -> {
            return null;
        }).when(salesService).store(sales);
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/sales"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/sales/" + sales.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void findLastOrder() throws Exception {
        mockMvc.perform(get("/sales/last"))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/sales").content(objectMapper.writeValueAsString(sales))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}