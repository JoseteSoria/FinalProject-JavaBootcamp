package com.ironhack.PharmacyEdge.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.PharmacyEdge.classes.Money;
import com.ironhack.PharmacyEdge.model.medicine.Medicine;
import com.ironhack.PharmacyEdge.model.medicine.WarehouseMedicine;
import com.ironhack.PharmacyEdge.model.medicine.viewModel.WarehouseMedicineQuantityVM;
import com.ironhack.PharmacyEdge.model.order.dto.MedicinesToStoreDTO;
import com.ironhack.PharmacyEdge.model.sell.dto.MedicinesToSellDTO;
import com.ironhack.PharmacyEdge.service.MedicineService;
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
class MedicineControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private MedicineService medicineService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Medicine medicine;
    private WarehouseMedicine warehouseMedicine;
    MedicinesToStoreDTO medicinesToStoreDTO;
    MedicinesToSellDTO medicinesToSellDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        medicine = new Medicine("Ibuprofeno", 18, true, new Money(new BigDecimal("3.45")));
        Medicine medicine2 = new Medicine("Paracetamol", 20, true, new Money(new BigDecimal("3.85")));

        List<Medicine> medicines = Arrays.asList(medicine, medicine2);
        when(medicineService.findAllMedicines()).thenReturn(medicines);
        when(medicineService.findMedicineById(medicine.getId())).thenReturn(medicine);
        when(medicineService.findMedicineByName("Ibuprofeno")).thenReturn(Optional.of(medicine));

        warehouseMedicine = new WarehouseMedicine(medicine);
        warehouseMedicine.setId(1l);
        WarehouseMedicine warehouseMedicine2 = new WarehouseMedicine("Paracetamol", 20, true, new Money(new BigDecimal("3.85")), new Money(new BigDecimal("3.95")));
        List<WarehouseMedicine> warehouseMedicines = Arrays.asList(warehouseMedicine, warehouseMedicine2);
        when(medicineService.findAllWarehouseMedicines()).thenReturn(warehouseMedicines);
        when(medicineService.findWarehouseMedicineById(warehouseMedicine.getId())).thenReturn(warehouseMedicine);
        WarehouseMedicineQuantityVM vm = new WarehouseMedicineQuantityVM("Ibuprofeno", 1);
        when(medicineService.findQuantityByName("Ibuprofeno")).thenReturn(Optional.of(vm));
        when(medicineService.findWarehouseMedicineByName("Ibuprofeno")).thenReturn(Optional.of(Collections.singletonList(warehouseMedicine)));
        medicinesToStoreDTO = new MedicinesToStoreDTO(1l, 3);
        medicinesToSellDTO = new MedicinesToSellDTO(123l, 2, 12);
        doAnswer(i -> {
            return null;
        }).when(medicineService).addWarehouseMedicines(Collections.singletonList(medicinesToStoreDTO));
        doAnswer(i -> {
            return null;
        }).when(medicineService).updatePrice(warehouseMedicine.getId(), "10");
        doAnswer(i -> {
            return null;
        }).when(medicineService).deleteWarehouseMedicine(warehouseMedicine.getId());
        doAnswer(i -> {
            return null;
        }).when(medicineService).removeWarehouseMedicinesMultiple(Collections.singletonList(medicinesToSellDTO));

    }

    @Test
    void findAllMedicines() throws Exception {
        mockMvc.perform(get("/medicines"))
                .andExpect(status().isOk());
    }

    @Test
    void findMedicineById() throws Exception {
        mockMvc.perform(get("/medicines/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findMedicineByName() throws Exception {
        mockMvc.perform(get("/medicines/name/Ibuprofeno"))
                .andExpect(status().isOk());
    }

    @Test
    void findAllWarehouseMedicines() throws Exception {
        mockMvc.perform(get("/warehouse-medicines"))
                .andExpect(status().isOk());
    }

    @Test
    void findWarehouseMedicineById() throws Exception {
        mockMvc.perform(get("/warehouse-medicines/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findWarehouseMedicineByName() throws Exception {
        mockMvc.perform(get("/warehouse-medicines/name/Ibuprofeno"))
                .andExpect(status().isOk());
    }

    @Test
    void findQuantityByName() throws Exception {
        mockMvc.perform(get("/warehouse-medicines/name/Ibuprofeno/quantity"))
                .andExpect(status().isOk());
    }

    @Test
    void addWarehouseMedicines() throws Exception {
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