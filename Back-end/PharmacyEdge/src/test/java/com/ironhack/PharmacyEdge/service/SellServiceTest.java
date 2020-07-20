package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.classes.Money;
import com.ironhack.PharmacyEdge.client.SellClient;
import com.ironhack.PharmacyEdge.exceptions.SellServiceDownException;
import com.ironhack.PharmacyEdge.model.sell.MedicineSold;
import com.ironhack.PharmacyEdge.model.sell.Sales;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class SellServiceTest {
    @Autowired
    private SellService sellService;

    @MockBean
    private SellClient sellClient;

    private Sales sales, sales2;
    private MedicineSold medicineSold, medicineSold2, medicineSold3;

    @BeforeEach
    void setUp() {
        sales = new Sales(1, 1, new Money(new BigDecimal("12.5")));
        sales.setId(1l);
        sales2 = new Sales(1, 1, new Money(new BigDecimal("10")));
        List<Sales> salesList = Arrays.asList(sales2, sales);
        when(sellClient.findAllSales()).thenReturn(salesList);
        when(sellClient.findSaleById(sales.getId())).thenReturn(sales);
        when(sellClient.findLastSale()).thenReturn(sales2);
        doAnswer(i -> {
            return null;
        }).when(sellClient).createSale(sales);

        medicineSold = new MedicineSold(1l, "Ibuprofeno", 1l);
        medicineSold.setId(1l);
        medicineSold2 = new MedicineSold(2l, "Paracetamol", 1l);
        List<MedicineSold> medicines = Arrays.asList(medicineSold, medicineSold2);
        when(sellClient.findAllMedicinesSold()).thenReturn(medicines);
        when(sellClient.findMedicineSoldById(medicineSold.getId())).thenReturn(medicineSold);
        when(sellClient.findMedicineSoldBySalesId(1l)).thenReturn(medicines);
        medicineSold3 = new MedicineSold(3l, "Amoxicilina", 1l);
        doAnswer(i -> {
            return null;
        }).when(sellClient).createMedicineSold(Collections.singletonList(medicineSold3));
    }

    @Test
    @DisplayName("Unit test - retrieval of all sales")
    void findAllSales() {
        List<Sales> salesList = sellService.findAllSales();
        assertEquals(2, salesList.size());
    }

    @Test
    void errorFindAllSales() {
        assertThrows(SellServiceDownException.class, () -> sellService.errorFindAllSales());
    }

    @Test
    @DisplayName("Unit test - retrieval of a sale with specific id")
    void findSaleById() {
        assertEquals(sales, sellService.findSaleById(1l));
    }

    @Test
    void errorFindSaleById() {
        assertThrows(SellServiceDownException.class, () -> sellService.errorFindSaleById(null));
    }

    @Test
    @DisplayName("Unit test - retrieval the last sale")
    void findLastSale() {
        assertEquals(sales2, sellService.findLastSale());
    }

    @Test
    void errorFindLastSale() {
        assertThrows(SellServiceDownException.class, () -> sellService.errorFindLastSale());
    }

    @Test
    @DisplayName("Unit test - create a new sale")
    void createSale() {
        sellService.createSale(sales);
    }

    @Test
    void errorCreateSale() {
        assertThrows(SellServiceDownException.class, () -> sellService.errorCreateSale(null));
    }

    @Test
    @DisplayName("Unit test - retrieval of all medicines-sold")
    void findAllMedicinesSold() {
        List<MedicineSold> medicineOrderedList = sellService.findAllMedicinesSold();
        assertEquals(2, medicineOrderedList.size());
    }

    @Test
    void errorFindAllMedicinesSold() {
        assertThrows(SellServiceDownException.class, () -> sellService.errorFindAllMedicinesSold());
    }

    @Test
    @DisplayName("Unit test - retrieval of a medicine-sold with specific id")
    void findMedicineSoldById() {
        assertEquals(medicineSold, sellService.findMedicineSoldById(medicineSold.getId()));
    }

    @Test
    void errorFindMedicineSoldById() {
        assertThrows(SellServiceDownException.class, () -> sellService.errorFindMedicineSoldById(null));
    }

    @Test
    @DisplayName("Unit test - retrieval of a medicine-sold with specific salesId")
    void findMedicineSoldBySalesId() {
        List<MedicineSold> medicineOrderedList = sellService.findMedicineSoldBySalesId(medicineSold.getSalesId());
        assertEquals(2, medicineOrderedList.size());
    }

    @Test
    void errorFindMedicineSoldBySalesId() {
        assertThrows(SellServiceDownException.class, () -> sellService.errorFindMedicineSoldBySalesId(null));
    }

    @Test
    @DisplayName("Unit test - add a new medicine-sold")
    void createMedicineSold() {
        sellService.storeMedicinesSold(Collections.singletonList(medicineSold3));
    }

    @Test
    void errorCreateMedicineSold() {
        assertThrows(SellServiceDownException.class, () -> sellService.errorStoreMedicinesSold(null));
    }
}