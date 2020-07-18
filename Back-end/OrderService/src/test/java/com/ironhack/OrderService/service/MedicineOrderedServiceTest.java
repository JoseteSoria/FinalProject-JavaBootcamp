package com.ironhack.OrderService.service;

import com.ironhack.OrderService.exceptions.ResourceNotFoundException;
import com.ironhack.OrderService.model.MedicineOrdered;
import com.ironhack.OrderService.model.Order;
import com.ironhack.OrderService.repository.MedicineOrderedRepository;
import com.ironhack.OrderService.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class MedicineOrderedServiceTest {
    @Autowired
    private MedicineOrderedService medicineOrderedService;

    @MockBean
    private MedicineOrderedRepository medicineOrderedRepository;
    @MockBean
    private OrderRepository orderRepository;

    private MedicineOrdered medicineOrdered, medicineOrdered2, medicineOrdered3;

    @BeforeEach
    void setUp() {
        medicineOrdered = new MedicineOrdered(1l, 1l, 10);
        medicineOrdered.setId(1l);
        medicineOrdered2 = new MedicineOrdered(1l, 2l, 5);

        List<MedicineOrdered> medicines = Arrays.asList(medicineOrdered, medicineOrdered2);
        when(medicineOrderedRepository.findAllByOrderByOrderIdDesc()).thenReturn(medicines);
        when(medicineOrderedRepository.findById(medicineOrdered.getId())).thenReturn(Optional.of(medicineOrdered));
        Order order = new Order();
        order.setId(1l);
        when(orderRepository.findById(medicineOrdered.getOrderId())).thenReturn(Optional.of(order));
        when(medicineOrderedRepository.findAllByOrderIdEquals(1l)).thenReturn(medicines);
        medicineOrdered3 = new MedicineOrdered(1l, 3l, 5);
        doAnswer(i -> {
            return null;
        }).when(medicineOrderedRepository).saveAll(Collections.singletonList(medicineOrdered3));
    }

    @Test
    @DisplayName("Unit test - retrieval of all medicines-ordered")
    void findAll() {
        List<MedicineOrdered> medicineOrderedList = medicineOrderedService.findAll();
        assertEquals(2, medicineOrderedList.size());
    }

    @Test
    @DisplayName("Unit test - retrieval of a medicine-ordered with specific id")
    void findById() {
        assertEquals(medicineOrdered, medicineOrderedService.findById(medicineOrdered.getId()));
    }

    @Test
    @DisplayName("Unit test - not found a medicine-ordered with specific id")
    void findById_NotFound() {
        assertThrows(ResourceNotFoundException.class, () -> medicineOrderedService.findById(0l));
    }

    @Test
    @DisplayName("Unit test - retrieval of a medicine-ordered with specific orderId")
    void findByOrderId() {
        List<MedicineOrdered> medicineOrderedList = medicineOrderedService.findByOrderId(medicineOrdered.getOrderId());
        assertEquals(2, medicineOrderedList.size());
    }

    @Test
    @DisplayName("Unit test - retrieval of a medicine-ordered with orderId not found")
    void findByOrderId_OrderNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> medicineOrderedService.findByOrderId(0l));
    }

    @Test
    @DisplayName("Unit test - add a new medicine-ordered")
    void create() {
        medicineOrderedService.storeMedicines(Collections.singletonList(medicineOrdered3));
    }
}