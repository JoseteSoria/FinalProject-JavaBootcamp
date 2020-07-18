package com.ironhack.MedicineService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.MedicineService.classes.Money;
import com.ironhack.MedicineService.exceptions.IllegalInputException;
import com.ironhack.MedicineService.exceptions.ResourceNotFoundException;
import com.ironhack.MedicineService.model.Medicine;
import com.ironhack.MedicineService.model.WarehouseMedicine;
import com.ironhack.MedicineService.model.viewModel.WarehouseMedicineQuantityVM;
import com.ironhack.MedicineService.repository.MedicineRepository;
import com.ironhack.MedicineService.repository.WarehouseMedicineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class WarehouseMedicineServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private WarehouseMedicineService warehouseMedicineService;

    @MockBean
    private WarehouseMedicineRepository warehouseMedicineRepository;
    @MockBean
    private MedicineRepository medicineRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private WarehouseMedicine warehouseMedicine, warehouseMedicine2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Medicine medicine = new Medicine("Ibuprofeno", 18, true, new Money(new BigDecimal("3.45")));
        warehouseMedicine = new WarehouseMedicine(medicine, 10);
        warehouseMedicine.setId(1l);
        warehouseMedicine2 = new WarehouseMedicine("Paracetamol", 20, true, new Money(new BigDecimal("3.85")), 12, new Money(new BigDecimal("3.95")));
        warehouseMedicine2.setId(2l);
        List<WarehouseMedicine> warehouseMedicines = Arrays.asList(warehouseMedicine, warehouseMedicine2);
        when(warehouseMedicineRepository.findAll()).thenReturn(warehouseMedicines);
        when(warehouseMedicineRepository.findById(warehouseMedicine.getId())).thenReturn(Optional.of(warehouseMedicine));
        WarehouseMedicineQuantityVM vm = new WarehouseMedicineQuantityVM("Ibuprofeno", 1);
        WarehouseMedicineQuantityVM [] vms = new WarehouseMedicineQuantityVM[1];
        vms[0] = vm;
        when(warehouseMedicineRepository.findMedicinesByName("Ibuprofeno")).thenReturn(Optional.of(vms));
        when(medicineRepository.findById(1l)).thenReturn(Optional.of(medicine));
        when(medicineRepository.findById(2l)).thenReturn(Optional.empty());
        Medicine medicine2 = new Medicine("Paracetamol", 20, true, new Money(new BigDecimal("3.85")));
        when(medicineRepository.findById(2l)).thenReturn(Optional.of(medicine2));
        doAnswer(i -> {return null;}).when(warehouseMedicineRepository).save(warehouseMedicine);
        doAnswer(i -> {return null;}).when(warehouseMedicineRepository).save(warehouseMedicine2);
        doAnswer(i -> {return null;}).when(warehouseMedicineRepository).delete(warehouseMedicine);
    }

    @Test
    @DisplayName("Unit test - retrieval of all warehouse-medicines")
    void findAll(){
        List<WarehouseMedicine> warehouseMedicines = warehouseMedicineService.findAll();
        assertEquals(2, warehouseMedicines.size());
    }

    @Test
    @DisplayName("Unit test - retrieval of a warehouse-medicine with specific id")
    void findById() {
        assertEquals("Ibuprofeno", warehouseMedicineService.findById(1l).getName());
    }

    @Test
    @DisplayName("Unit test - not found a warehouse-medicine with specific id")
    void findById_NotFound() {
        assertThrows(ResourceNotFoundException.class, ()->warehouseMedicineService.findById(0l));
    }

    @Test
    @DisplayName("Unit test - retrieval of a warehouse-medicine by name")
    void findByName() {
        assertEquals("Ibuprofeno", warehouseMedicineService.findByName("Ibuprofeno").get().getName());
    }

    @Test
    @DisplayName("Unit test - add quantity of a warehouse-medicine ")
    void addQuantity() throws Exception {
        warehouseMedicineService.addQuantity(warehouseMedicine.getId(),5);
    }

    @Test
    @DisplayName("Unit test - reduce quantity of a warehouse-medicine ")
    void reduceQuantity() throws Exception {
        warehouseMedicineService.reduceQuantity(warehouseMedicine.getId(),5);
    }

    @Test
    @DisplayName("Unit test - update price of a warehouse-medicine ")
    void updatePrice() throws Exception {
        warehouseMedicineService.updatePrice(warehouseMedicine.getId(), new BigDecimal("10"));
    }

    @Test
    void create_Correct() throws Exception {
        warehouseMedicineService.newStore(2l);
    }

    @Test
    void create_WarehouseMedicineAlreadyInDatabase_Exception() throws Exception {
        assertThrows(IllegalInputException.class, ()->warehouseMedicineService.newStore(warehouseMedicine.getId()));
    }

    @Test
    void create_MedicineNotFound_Exception() throws Exception {
        assertThrows(ResourceNotFoundException.class, ()->warehouseMedicineService.newStore(0l));
    }

    @Test
    void deleteWarehouseMedicine() throws Exception {
        warehouseMedicineService.delete(warehouseMedicine.getId());
    }

}