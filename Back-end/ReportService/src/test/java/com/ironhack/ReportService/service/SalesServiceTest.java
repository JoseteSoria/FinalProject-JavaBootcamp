package com.ironhack.ReportService.service;

import com.ironhack.ReportService.repository.SalesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class SalesServiceTest {

    @MockBean
    private SalesRepository salesRepository;

    @Autowired
    private SalesService salesService;

    @BeforeEach
    void setUp() {
        when(salesRepository.findSalesByUserInMonthsPeriod(any())).thenReturn(null);
        when(salesRepository.findPurchasesByPatientInMonthsPeriod(any())).thenReturn(null);
    }

    @Test
    void findSalesByUserInMonthsPeriod() {
        salesService.findSalesByUserInMonthsPeriod(1);
    }

    @Test
    void findSalesByUserInMonthsPeriod_MoreThan12Months() {
        salesService.findSalesByUserInMonthsPeriod(14);
    }

    @Test
    void findPurchasesByPatientInMonthsPeriod() {
        salesService.findPurchasesByPatientInMonthsPeriod(1);
    }

    @Test
    void findPurchasesByPatientInMonthsPeriod_MoreThan12Months() {
        salesService.findPurchasesByPatientInMonthsPeriod(14);
    }

}