package com.ironhack.ReportService.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.ReportService.service.SalesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SalesControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private SalesService salesService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        when(salesService.findSalesByUserInMonthsPeriod(1)).thenReturn(null);
        when(salesService.findPurchasesByPatientInMonthsPeriod(1)).thenReturn(null);
    }

    @Test
    void findSalesByUserInMonthsPeriod() throws Exception {
        this.mockMvc.perform(get("/reports/sales-users/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findPurchasesByPatientInMonthsPeriod() throws Exception {
        this.mockMvc.perform(get("/reports/purchases-patients/1"))
                .andExpect(status().isOk());
    }

}