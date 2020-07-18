package com.ironhack.PatientService.service;

import com.ironhack.PatientService.exceptions.ResourceNotFoundException;
import com.ironhack.PatientService.model.Patient;
import com.ironhack.PatientService.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @MockBean
    private PatientRepository patientRepository;

    private Patient patient, patient2;

    @BeforeEach
    void setUp() {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        Calendar cal = Calendar.getInstance();
        cal.set(1955,10,20);
        patient = new Patient("Jack Lemmon", cal, "674928467");
        patient.setId(10);
        cal.set(1953, 9, 15);
        patient2 = new Patient("Walter Matthau", cal, "687234820");
        List<Patient> patients = Arrays.asList(patient, patient2);
        when(patientRepository.findAll()).thenReturn(patients);
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        when(patientRepository.findByName("Jack Lemmon")).thenReturn(Optional.of(patient));
        when(patientRepository.save(patient2)).thenReturn(patient2);
        doAnswer(i -> {return null;}).when(patientRepository).delete(patient);
    }

    @Test
    @DisplayName("Unit test - retrieval of all patients")
    void findAll(){
        List<Patient> patientList = patientService.findAll();
        assertEquals(2, patientList.size());
    }

    @Test
    @DisplayName("Unit test - retrieval of a patient with specific id")
    void findById() {
        assertEquals("Jack Lemmon", patientService.findById(patient.getId()).getName());
    }

    @Test
    @DisplayName("Unit test - not found a patient with specific id")
    void findById_NotFound() {
        assertThrows(ResourceNotFoundException.class, ()-> patientService.findById(0));
    }

    @Test
    @DisplayName("Unit test - retrieval of a patient by name")
    void findByUsername() {
        assertEquals("Jack Lemmon", patientService.findByName("Jack Lemmon").get().getName());
    }

    @Test
    @DisplayName("Unit test - create a new patient")
    void createPatient() {
        assertEquals("Walter Matthau", patientService.store(patient2).getName());
    }

    @Test
    @DisplayName("Unit test - delete an existing patient")
    void deletePatient () {
        patientService.delete(patient.getId());
    }

}