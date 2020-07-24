package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.client.ReportClient;
import com.ironhack.PharmacyEdge.exceptions.ReportServiceDownException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReportServiceTest {
    @MockBean
    private ReportClient reportClient;

    @Autowired
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        when(reportClient.findSalesByUserInMonthsPeriod(any())).thenReturn(null);
        when(reportClient.findPurchasesByPatientInMonthsPeriod(any())).thenReturn(null);
        when(reportClient.findMoreMedicineSoldPeriodRanking(any(), any())).thenReturn(null);
        when(reportClient.findMoreMedicineRevenuePeriodRanking(any(), any())).thenReturn(null);
    }

    @Test
    void findSalesByUserInMonthsPeriod() {
        reportService.findSalesByUserInMonthsPeriod(1);
    }

    @Test
    void errorFindSalesByUserInMonthsPeriod() {
        assertThrows(ReportServiceDownException.class, () -> reportService.errorFindSalesByUserInMonthsPeriod(null));
    }

    @Test
    void findPurchasesByPatientInMonthsPeriod() {
        reportService.findPurchasesByPatientInMonthsPeriod(1);
    }

    @Test
    void errorFindPurchasesByPatientInMonthsPeriod() {
        assertThrows(ReportServiceDownException.class, () -> reportService.errorFindPurchasesByPatientInMonthsPeriod(null));
    }

    @Test
    void findMoreMedicineSoldPeriodRanking() {
        reportService.findMoreMedicineSoldPeriodRanking(6, 2);
    }

    @Test
    void errorFindMoreMedicineSoldPeriodRanking() {
        assertThrows(ReportServiceDownException.class, () -> reportService.errorFindMoreMedicineSoldPeriodRanking(null, null));
    }

    @Test
    void findMoreMedicineRevenuePeriodRanking() {
        reportService.findMoreMedicineRevenuePeriodRanking(6, 2);
    }

    @Test
    void errorFindMoreMedicineRevenuePeriodRanking() {
        assertThrows(ReportServiceDownException.class, () -> reportService.errorFindMoreMedicineRevenuePeriodRanking(null, null));
    }

}