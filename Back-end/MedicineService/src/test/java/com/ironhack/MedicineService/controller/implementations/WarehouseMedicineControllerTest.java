package com.ironhack.MedicineService.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MedicineService.classes.Money;
import com.ironhack.MedicineService.model.Medicine;
import com.ironhack.MedicineService.model.WarehouseMedicine;
import com.ironhack.MedicineService.service.WarehouseMedicineService;
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

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class WarehouseMedicineControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private WarehouseMedicineService warehouseMedicineService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private WarehouseMedicine warehouseMedicine;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Medicine medicine = new Medicine("Ibuprofeno", 18, true, new Money(new BigDecimal("3.45")));
        warehouseMedicine = new WarehouseMedicine(medicine, 10);
        warehouseMedicine.setId(1l);
        WarehouseMedicine warehouseMedicine2 = new WarehouseMedicine("Paracetamol", 20, true, new Money(new BigDecimal("3.85")), 12, new Money(new BigDecimal("3.95")));
        List<WarehouseMedicine> warehouseMedicines = Arrays.asList(warehouseMedicine, warehouseMedicine2);
        when(warehouseMedicineService.findAll()).thenReturn(warehouseMedicines);
        when(warehouseMedicineService.findById(warehouseMedicine.getId())).thenReturn(warehouseMedicine);
        when(warehouseMedicineService.findByName("Ibuprofeno")).thenReturn(Optional.of(warehouseMedicine));
        doAnswer(i -> {return null;}).when(warehouseMedicineService).addQuantity(warehouseMedicine.getId(),5);
        doAnswer(i -> {return null;}).when(warehouseMedicineService).reduceQuantity(warehouseMedicine.getId(),5);
        doAnswer(i -> {return null;}).when(warehouseMedicineService).updatePrice(warehouseMedicine.getId(),new BigDecimal("10"));
        when(warehouseMedicineService.newStore(warehouseMedicine.getId())).thenReturn(warehouseMedicine);
        doAnswer(i -> {return null;}).when(warehouseMedicineService).delete(warehouseMedicine.getId());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/warehouse-medicines"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/warehouse-medicines/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findByName() throws Exception {
        mockMvc.perform(get("/warehouse-medicines/name/Ibuprofeno"))
                .andExpect(status().isOk());
    }

    @Test
    void addQuantity() throws Exception {
        mockMvc.perform(put("/warehouse-medicines/1/add/5"))
                .andExpect(status().isNoContent());
    }

    @Test
    void reduceQuantity() throws Exception {
        mockMvc.perform(put("/warehouse-medicines/1/reduce/5"))
                .andExpect(status().isNoContent());
    }

    @Test
    void updatePrice() throws Exception {
        mockMvc.perform(put("/warehouse-medicines/1/update-price/10"))
                .andExpect(status().isNoContent());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/warehouse-medicines/create/1"))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteWarehouseMedicine() throws Exception {
        mockMvc.perform(delete("/warehouse-medicines/delete/1"))
                .andExpect(status().isNoContent());
    }
}