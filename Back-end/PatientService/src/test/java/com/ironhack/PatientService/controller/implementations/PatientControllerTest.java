package com.ironhack.PatientService.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.PatientService.model.Patient;
import com.ironhack.PatientService.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PatientControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PatientService patientService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Patient patient, patient2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        Calendar cal = Calendar.getInstance();
        cal.set(1955,10,20);
        patient = new Patient("Jack Lemmon", cal, "674928467");
        patient.setId(10);
        cal.set(1953, 9, 15);
        patient2 = new Patient("Waler Matthau", cal, "687234820");
        List<Patient> patients = Arrays.asList(patient, patient2);
        when(patientService.findAll()).thenReturn(patients);
        when(patientService.findById(patient.getId())).thenReturn(patient);
        when(patientService.findByName("Jack Lemmon")).thenReturn(Optional.of(patient));
        when(patientService.store(patient2)).thenReturn(patient2);
        doAnswer(i -> {return null;}).when(patientService).delete(patient.getId());

    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk());
    }

    @Test
    void findByName() throws Exception {
        MvcResult result = mockMvc.perform(get("/patients/name/Jack Lemmon"))
                .andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/patients").content(objectMapper.writeValueAsString(patient2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deletePatient() throws Exception {
        mockMvc.perform(delete("/patients/" + patient.getId())).andExpect(status().isNoContent());
    }

}