package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.client.PatientClient;
import com.ironhack.PharmacyEdge.exceptions.PatientServiceDownException;
import com.ironhack.PharmacyEdge.model.patient.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @MockBean
    private PatientClient patientClient;

    private Patient patient, patient2;

    @BeforeEach
    void setUp() {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        Calendar cal = Calendar.getInstance();
        cal.set(1955, 10, 20);
        patient = new Patient("Jack Lemmon", cal, "674928467");
        patient.setId(10);
        cal.set(1953, 9, 15);
        patient2 = new Patient("Walter Matthau", cal, "687234820");
        List<Patient> patients = Arrays.asList(patient, patient2);
        when(patientClient.findAll()).thenReturn(patients);
        when(patientClient.findById(patient.getId())).thenReturn(patient);
        when(patientClient.findPatientByName("Jack Lemmon")).thenReturn(Optional.of(patient));
        when(patientClient.create(patient2)).thenReturn(patient2);
        doAnswer(i -> {
            return null;
        }).when(patientClient).delete(patient.getId());
    }

    @Test
    @DisplayName("Unit test - retrieval of all patients")
    void findAll() {
        List<Patient> patientList = patientService.findAll();
        assertEquals(2, patientList.size());
    }

    @Test
    void errorFindAll() {
        assertThrows(PatientServiceDownException.class, () -> patientService.errorFindAll());
    }

    @Test
    @DisplayName("Unit test - retrieval of a patient with specific id")
    void findById() {
        assertEquals("Jack Lemmon", patientService.findById(patient.getId()).getName());
    }

    @Test
    void errorFindById() {
        assertThrows(PatientServiceDownException.class, () -> patientService.errorFindById(0));
    }

    @Test
    @DisplayName("Unit test - retrieval of a patient by name")
    void findByName() {
        assertEquals("Jack Lemmon", patientService.findPatientByName("Jack Lemmon").get().getName());
    }

    @Test
    void errorFindByName() {
        assertThrows(PatientServiceDownException.class, () -> patientService.errorFindPatientByName("lsdflas"));
    }

    @Test
    @DisplayName("Unit test - create a new patient")
    void createPatient() {
        assertEquals("Walter Matthau", patientService.create(patient2).getName());
    }

    @Test
    void errorCreatePatient() {
        assertThrows(PatientServiceDownException.class, () -> patientService.errorCreate(null));
    }

    @Test
    @DisplayName("Unit test - delete an existing patient")
    void deletePatient() {
        patientService.delete(patient.getId());
    }

    @Test
    void errorDeletePatient() {
        assertThrows(PatientServiceDownException.class, () -> patientService.errorDelete(null));
    }

}