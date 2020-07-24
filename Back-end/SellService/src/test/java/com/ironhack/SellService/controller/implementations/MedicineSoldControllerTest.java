package com.ironhack.SellService.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.SellService.classes.Money;
import com.ironhack.SellService.model.MedicineSold;
import com.ironhack.SellService.service.MedicineSoldService;
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
class MedicineSoldControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private MedicineSoldService medicineSoldService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MedicineSold medicineSold, medicineSold3;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        medicineSold = new MedicineSold(1l, "Ibuprofeno",1l);
        medicineSold.setId(1l);
        MedicineSold medicineSold2 = new MedicineSold(2l, "Parecetamol", new Money(new BigDecimal("2.50")), 1l);

        List<MedicineSold> medicines = Arrays.asList(medicineSold, medicineSold2);
        when(medicineSoldService.findAll()).thenReturn(medicines);
        when(medicineSoldService.findById(medicineSold.getId())).thenReturn(medicineSold);
        when(medicineSoldService.findBySalesId(1l)).thenReturn(medicines);
        medicineSold3 = new MedicineSold(3l, "Amoxicilina", 2l);
        doAnswer(i -> {
            return null;
        }).when(medicineSoldService).storeMedicinesSold(Collections.singletonList(medicineSold3));
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/medicines-sold"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/medicines-sold/" + medicineSold.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void findByOrderId() throws Exception {
        mockMvc.perform(get("/medicines-sold/sales/" + medicineSold.getSalesId()))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/medicines-sold").content(objectMapper.writeValueAsString(Collections.singletonList(medicineSold3)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}