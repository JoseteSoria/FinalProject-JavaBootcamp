package com.ironhack.OrderService.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.OrderService.model.MedicineOrdered;
import com.ironhack.OrderService.service.MedicineOrderedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MedicineOrderedControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private MedicineOrderedService medicineOrderedService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private MedicineOrdered medicineOrdered, medicineOrdered3;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        medicineOrdered = new MedicineOrdered(1l,1l,10);
        medicineOrdered.setId(1l);
        medicineOrdered.setMedicineName("Ibuprofeno");
        MedicineOrdered medicineOrdered2 = new MedicineOrdered(1l,2l,5);

        List<MedicineOrdered> medicines = Arrays.asList(medicineOrdered, medicineOrdered2);
        when(medicineOrderedService.findAll()).thenReturn(medicines);
        when(medicineOrderedService.findById(medicineOrdered.getId())).thenReturn(medicineOrdered);
        when(medicineOrderedService.findByOrderId(1l)).thenReturn(medicines);
        medicineOrdered3 = new MedicineOrdered(1l,3l, "Nolotil", 5);
        doAnswer(i -> {
            return null;
        }).when(medicineOrderedService).storeMedicines(Collections.singletonList(medicineOrdered3));
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/medicines-ordered"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/medicines-ordered/" + medicineOrdered.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void findByOrderId() throws Exception {
        mockMvc.perform(get("/medicines-ordered/order/" + medicineOrdered.getOrderId()))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/medicines-ordered").content(objectMapper.writeValueAsString(Collections.singletonList(medicineOrdered3)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}