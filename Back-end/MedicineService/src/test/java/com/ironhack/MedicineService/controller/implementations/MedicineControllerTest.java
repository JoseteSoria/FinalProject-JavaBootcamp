package com.ironhack.MedicineService.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MedicineService.classes.Money;
import com.ironhack.MedicineService.model.Medicine;
import com.ironhack.MedicineService.service.MedicineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MedicineControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private MedicineService medicineService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Medicine medicine;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        medicine = new Medicine("Ibuprofeno", 18, true, new Money(new BigDecimal("3.45")));
        Medicine medicine2 = new Medicine("Paracetamol", 20, true, new Money(new BigDecimal("3.85")));

        List<Medicine> medicines = Arrays.asList(medicine, medicine2);
        when(medicineService.findAll()).thenReturn(medicines);
        when(medicineService.findById(medicine.getId())).thenReturn(medicine);
        when(medicineService.findByName("Ibuprofeno")).thenReturn(Optional.of(medicine));
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/medicines"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/medicines/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findByName() throws Exception {
        mockMvc.perform(get("/medicines/name/Ibuprofeno"))
                .andExpect(status().isOk());
    }

}