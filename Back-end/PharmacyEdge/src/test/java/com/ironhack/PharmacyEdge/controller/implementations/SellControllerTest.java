package com.ironhack.PharmacyEdge.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.PharmacyEdge.classes.Money;
import com.ironhack.PharmacyEdge.model.sell.MedicineSold;
import com.ironhack.PharmacyEdge.model.sell.Sales;
import com.ironhack.PharmacyEdge.service.SellService;
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
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SellControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private SellService sellService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Sales sales;
    private MedicineSold medicineSold, medicineSold3;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        sales = new Sales(1, 1, new Money(new BigDecimal("12.5")));
        sales.setId(1l);
        Sales sales2 = new Sales(1, 1, new Money(new BigDecimal("10")));
        List<Sales> salesList = Arrays.asList(sales, sales2);
        when(sellService.findAllSales()).thenReturn(salesList);
        when(sellService.findSaleById(sales.getId())).thenReturn(sales);
        when(sellService.findLastSale()).thenReturn(sales2);
        doAnswer(i -> {
            return null;
        }).when(sellService).createSale(sales);

        medicineSold = new MedicineSold(1l, "Ibuprofeno", 1l);
        medicineSold.setId(1l);
        MedicineSold medicineSold2 = new MedicineSold(2l, "Parecetamol", new Money(new BigDecimal("2.50")),1l);
        List<MedicineSold> medicines = Arrays.asList(medicineSold, medicineSold2);
        when(sellService.findAllMedicinesSold()).thenReturn(medicines);
        when(sellService.findMedicineSoldById(medicineSold.getId())).thenReturn(medicineSold);
        when(sellService.findMedicineSoldBySalesId(1l)).thenReturn(medicines);
        medicineSold3 = new MedicineSold(3l, "Amoxicilina", 2l);
        doAnswer(i -> {
            return null;
        }).when(sellService).storeMedicinesSold(Collections.singletonList(medicineSold3));
    }

    @Test
    void findAllSales() throws Exception {
        mockMvc.perform(get("/sales"))
                .andExpect(status().isOk());
    }

    @Test
    void findSaleById() throws Exception {
        mockMvc.perform(get("/sales/" + sales.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void findLastOrder() throws Exception {
        mockMvc.perform(get("/sales/last"))
                .andExpect(status().isOk());
    }

    @Test
    void createSale() throws Exception {
        mockMvc.perform(post("/sales").content(objectMapper.writeValueAsString(sales))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void findAllMedicinesSold() throws Exception {
        mockMvc.perform(get("/medicines-sold"))
                .andExpect(status().isOk());
    }

    @Test
    void findMedicineSoldById() throws Exception {
        mockMvc.perform(get("/medicines-sold/" + medicineSold.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void findMedicineSoldBySalesId() throws Exception {
        mockMvc.perform(get("/medicines-sold/sales/" + medicineSold.getSalesId()))
                .andExpect(status().isOk());
    }

    @Test
    void storeMedicinesSold() throws Exception {
        mockMvc.perform(post("/medicines-sold").content(objectMapper.writeValueAsString(Collections.singletonList(medicineSold3)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}