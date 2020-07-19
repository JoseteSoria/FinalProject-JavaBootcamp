package com.ironhack.SellService.service;

import com.ironhack.SellService.classes.Money;
import com.ironhack.SellService.exceptions.ResourceNotFoundException;
import com.ironhack.SellService.model.MedicineSold;
import com.ironhack.SellService.model.Sales;
import com.ironhack.SellService.repository.MedicineSoldRepository;
import com.ironhack.SellService.repository.SalesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class MedicineSoldServiceTest {
    @Autowired
    private MedicineSoldService medicineSoldService;

    @MockBean
    private MedicineSoldRepository medicineSoldRepository;
    @MockBean
    private SalesRepository salesRepository;

    private MedicineSold medicineSold, medicineSold2, medicineSold3;

    @BeforeEach
    void setUp() {
        medicineSold = new MedicineSold(1l, "Ibuprofeno", 1l);
        medicineSold.setId(1l);
        medicineSold2 = new MedicineSold(2l, "Paracetamol", 1l);

        List<MedicineSold> medicines = Arrays.asList(medicineSold, medicineSold2);
        when(medicineSoldRepository.findAllByOrderBySalesIdDesc()).thenReturn(medicines);
        when(medicineSoldRepository.findById(medicineSold.getId())).thenReturn(Optional.of(medicineSold));
        Sales sales = new Sales(1, 1, new Money(new BigDecimal("5.6")));
        sales.setId(1l);
        when(salesRepository.findById(medicineSold.getSalesId())).thenReturn(Optional.of(sales));
        when(medicineSoldRepository.findAllBySalesIdEquals(1l)).thenReturn(medicines);
        medicineSold3 = new MedicineSold(3l, "Amoxicilina", 1l);
        doAnswer(i -> {
            return null;
        }).when(medicineSoldRepository).saveAll(Collections.singletonList(medicineSold3));
    }

    @Test
    @DisplayName("Unit test - retrieval of all medicines-sold")
    void findAll() {
        List<MedicineSold> medicineOrderedList = medicineSoldService.findAll();
        assertEquals(2, medicineOrderedList.size());
    }

    @Test
    @DisplayName("Unit test - retrieval of a medicine-sold with specific id")
    void findById() {
        assertEquals(medicineSold, medicineSoldService.findById(medicineSold.getId()));
    }

    @Test
    @DisplayName("Unit test - not found a medicine-sold with specific id")
    void findById_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> medicineSoldService.findById(0l));
    }

    @Test
    @DisplayName("Unit test - retrieval of a medicine-sold with specific orderId")
    void findByOrderId() {
        List<MedicineSold> medicineOrderedList = medicineSoldService.findBySalesId(medicineSold.getSalesId());
        assertEquals(2, medicineOrderedList.size());
    }

    @Test
    @DisplayName("Unit test - retrieval of a medicine-sold with orderId not found")
    void findByOrderId_OrderNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> medicineSoldService.findBySalesId(0l));
    }

    @Test
    @DisplayName("Unit test - add a new medicine-sold")
    void create() {
        medicineSoldService.storeMedicinesSold(Collections.singletonList(medicineSold3));
    }
}