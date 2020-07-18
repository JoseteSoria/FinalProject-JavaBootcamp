package com.ironhack.SellService.service;

import com.ironhack.SellService.classes.Money;
import com.ironhack.SellService.exceptions.ResourceNotFoundException;
import com.ironhack.SellService.model.Sales;
import com.ironhack.SellService.repository.SalesRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class SalesServiceTest {
    @Autowired
    private SalesService salesService;

    @MockBean
    private SalesRepository salesRepository;

    private Sales sales, sales2;

    @BeforeEach
    void setUp() {
        sales = new Sales(1, 1, new Money(new BigDecimal("12.5")));
        sales.setId(1l);
        sales2 = new Sales(1, 1, new Money(new BigDecimal("10")));

        List<Sales> salesList = Arrays.asList(sales2, sales);
        when(salesRepository.findAllByOrderByDateDesc()).thenReturn(salesList);
        when(salesRepository.findById(sales.getId())).thenReturn(Optional.of(sales));
        when(salesRepository.findLastSale()).thenReturn(sales2);
        doAnswer(i -> {
            return null;
        }).when(salesRepository).save(sales);
    }

    @Test
    @DisplayName("Unit test - retrieval of all sales")
    void findAll() {
        List<Sales> salesList = salesService.findAll();
        assertEquals(2, salesList.size());
    }

    @Test
    @DisplayName("Unit test - retrieval of a sale with specific id")
    void findById() {
        assertEquals(sales, salesService.findById(1l));
    }

    @Test
    @DisplayName("Unit test - not found a sale with that id")
    void findById_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> salesService.findById(0l));
    }

    @Test
    @DisplayName("Unit test - retrieval the last sale")
    void findLastOrder() {
        assertEquals(sales2, salesService.findLastSale());
    }

    @Test
    @DisplayName("Unit test - create a new sale")
    void create() {
        salesService.store(sales);
    }
}