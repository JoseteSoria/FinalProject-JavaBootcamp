package com.ironhack.MedicineService.service;

import com.ironhack.MedicineService.classes.Money;
import com.ironhack.MedicineService.exceptions.ResourceNotFoundException;
import com.ironhack.MedicineService.model.Medicine;
import com.ironhack.MedicineService.repository.MedicineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicineServiceTest {
    @Autowired
    private MedicineService medicineService;

    @MockBean
    private MedicineRepository medicineRepository;

    private Medicine medicine;

    @BeforeEach
    void setUp() {
        medicine = new Medicine("Ibuprofeno", 18, true, new Money(new BigDecimal("3.45")));
        medicine.setId(1l);
        Medicine medicine2 = new Medicine("Paracetamol", 20, true, new Money(new BigDecimal("3.85")));
        List<Medicine> medicines = Arrays.asList(medicine, medicine2);
        // mock responses
        when(medicineRepository.findAll()).thenReturn(medicines);
        when(medicineRepository.findById(1l)).thenReturn(Optional.of(medicine));
        when(medicineRepository.findByName("Ibuprofeno")).thenReturn(Optional.of(medicine));
    }

    @Test
    @DisplayName("Unit test - retrieval of all medicines")
    void findAll(){
        List<Medicine> medicineList = medicineService.findAll();
        assertEquals(2, medicineList.size());
    }

    @Test
    @DisplayName("Unit test - retrieval of a medicine with specific id")
    void findById() {
        assertEquals("Ibuprofeno", medicineService.findById(1l).getName());
    }

    @Test
    @DisplayName("Unit test - not found a medicine with specific id")
    void findById_NotFound() {
        assertThrows(ResourceNotFoundException.class, ()->medicineService.findById(0l));
    }

    @Test
    @DisplayName("Unit test - retrieval of a medicine by name")
    void findMedicineByName() {
        assertEquals("Ibuprofeno", medicineService.findByName("Ibuprofeno").get().getName());
    }

}