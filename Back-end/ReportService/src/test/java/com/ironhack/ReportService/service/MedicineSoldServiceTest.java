package com.ironhack.ReportService.service;

import com.ironhack.ReportService.repository.MedicineSoldRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class MedicineSoldServiceTest {

    @MockBean
    private MedicineSoldRepository medicineSoldRepository;

    @Autowired
    private MedicineSoldService medicineSoldService;

    @BeforeEach
    void setUp() {
        when(medicineSoldRepository.findMoreMedicineSoldPeriodRanking(any(), any())).thenReturn(null);
        when(medicineSoldRepository.findMoreMedicineRevenuePeriodRanking(any(), any())).thenReturn(null);
    }

    @Test
    void findMoreMedicineSoldPeriodRanking() {
        medicineSoldService.findMoreMedicineSoldPeriodRanking(6, 2);
    }

    @Test
    void findMoreMedicineSoldPeriodRanking_MoreThan12Months() {
        medicineSoldService.findMoreMedicineSoldPeriodRanking(14, 2);
    }

    @Test
    void findMoreMedicineRevenuePeriodRanking() {
        medicineSoldService.findMoreMedicineRevenuePeriodRanking(6, 2);
    }

    @Test
    void findMoreMedicineRevenuePeriodRanking_MoreThan12Months() {
        medicineSoldService.findMoreMedicineRevenuePeriodRanking(14, 2);
    }

}