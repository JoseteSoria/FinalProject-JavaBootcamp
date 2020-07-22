package com.ironhack.PharmacyEdge.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.PharmacyEdge.service.ReportService;
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
class ReportControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        when(reportService.findPurchasesByPatientInMonthsPeriod(1)).thenReturn(null);
        when(reportService.findSalesByUserInMonthsPeriod(1)).thenReturn(null);
        when(reportService.findMoreMedicineSoldPeriodRanking(1, 1)).thenReturn(null);
        when(reportService.findMoreMedicineRevenuePeriodRanking(1, 1)).thenReturn(null);
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

    @Test
    void findMoreMedicineSoldPeriodRanking() throws Exception {
        this.mockMvc.perform(get("/reports/medicines-sold/1/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findMoreMedicineRevenuePeriodRanking() throws Exception {
        this.mockMvc.perform(get("/reports/medicines-revenue/1/1"))
                .andExpect(status().isOk());
    }
}