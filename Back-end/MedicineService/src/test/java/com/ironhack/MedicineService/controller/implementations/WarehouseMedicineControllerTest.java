package com.ironhack.MedicineService.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MedicineService.classes.Money;
import com.ironhack.MedicineService.model.Medicine;
import com.ironhack.MedicineService.model.WarehouseMedicine;
import com.ironhack.MedicineService.model.dto.MedicinesToSellDTO;
import com.ironhack.MedicineService.model.dto.MedicinesToStoreDTO;
import com.ironhack.MedicineService.model.viewModel.WarehouseMedicineQuantityVM;
import com.ironhack.MedicineService.service.WarehouseMedicineService;
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
    MedicinesToStoreDTO medicinesToStoreDTO;
    MedicinesToSellDTO medicinesToSellDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Medicine medicine = new Medicine("Ibuprofeno", 18, true, new Money(new BigDecimal("3.45")));
        warehouseMedicine = new WarehouseMedicine(medicine);
        warehouseMedicine.setId(1l);
        WarehouseMedicine warehouseMedicine2 = new WarehouseMedicine("Paracetamol", 20, true, new Money(new BigDecimal("3.85")), new Money(new BigDecimal("3.95")));
        List<WarehouseMedicine> warehouseMedicines = Arrays.asList(warehouseMedicine, warehouseMedicine2);
        when(warehouseMedicineService.findAll()).thenReturn(warehouseMedicines);
        when(warehouseMedicineService.findById(warehouseMedicine.getId())).thenReturn(warehouseMedicine);
        WarehouseMedicineQuantityVM vm = new WarehouseMedicineQuantityVM("Ibuprofeno", 1);
        when(warehouseMedicineService.findQuantityByName("Ibuprofeno")).thenReturn(Optional.of(vm));
        when(warehouseMedicineService.findByName("Ibuprofeno")).thenReturn(Optional.of(Collections.singletonList(warehouseMedicine)));
        when(warehouseMedicineService.findMedicinesCloseToExpirationDate(6)).thenReturn(Optional.of(Collections.singletonList(warehouseMedicine)));
        doAnswer(i -> {
            return null;
        }).when(warehouseMedicineService).addWarehouseMedicines(warehouseMedicine.getId(), 5);
        medicinesToStoreDTO = new MedicinesToStoreDTO(2l, 1);
        medicinesToSellDTO = new MedicinesToSellDTO(123l, 2, 12);
        doAnswer(i -> {
            return null;
        }).when(warehouseMedicineService).addWarehouseMedicinesMultiple(Collections.singletonList(medicinesToStoreDTO));
        doAnswer(i -> {
            return null;
        }).when(warehouseMedicineService).updatePriceByNameId(warehouseMedicine.getId(), "10");
        doAnswer(i -> {
            return null;
        }).when(warehouseMedicineService).delete(warehouseMedicine.getId());
        doAnswer(i -> {
            return null;
        }).when(warehouseMedicineService).removeWarehouseMedicinesMultiple(Collections.singletonList(medicinesToSellDTO));
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
    void findQuantityByName() throws Exception {
        mockMvc.perform(get("/warehouse-medicines/name/Ibuprofeno/quantity"))
                .andExpect(status().isOk());
    }

    @Test
    void findMedicinesCloseToExpirationDate() throws Exception {
        mockMvc.perform(get("/warehouse-medicines/near-expiration/6"))
                .andExpect(status().isOk());
    }

    @Test
    void addQuantity() throws Exception {
        mockMvc.perform(post("/warehouse-medicines/add").content(objectMapper.writeValueAsString(Collections.singletonList(medicinesToStoreDTO)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updatePrice() throws Exception {
        mockMvc.perform(put("/warehouse-medicines/1/update-price/10"))
                .andExpect(status().isNoContent());
    }

    @Test
    void updatePrice_BelowMinimum() throws Exception {
        mockMvc.perform(put("/warehouse-medicines/1/update-price/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteWarehouseMedicine() throws Exception {
        mockMvc.perform(delete("/warehouse-medicines/delete/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void removeWarehouseMedicinesMultiple() throws Exception {
        mockMvc.perform(delete("/warehouse-medicines/delete").content(objectMapper.writeValueAsString(Collections.singletonList(medicinesToSellDTO)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}