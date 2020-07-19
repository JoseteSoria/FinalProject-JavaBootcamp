package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.classes.Money;
import com.ironhack.PharmacyEdge.client.MedicineClient;
import com.ironhack.PharmacyEdge.exceptions.MedicineServiceDownException;
import com.ironhack.PharmacyEdge.model.medicine.Medicine;
import com.ironhack.PharmacyEdge.model.medicine.WarehouseMedicine;
import com.ironhack.PharmacyEdge.model.medicine.viewModel.WarehouseMedicineQuantityVM;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class MedicineServiceTest {
    @Autowired
    private MedicineService medicineService;

    @MockBean
    private MedicineClient medicineClient;

    private Medicine medicine;
    private WarehouseMedicine warehouseMedicine, warehouseMedicine2;

    @BeforeEach
    void setUp() {
        medicine = new Medicine("Ibuprofeno", 18, true, new Money(new BigDecimal("3.45")));
        medicine.setId(1l);
        Medicine medicine2 = new Medicine("Paracetamol", 20, true, new Money(new BigDecimal("3.85")));
        List<Medicine> medicines = Arrays.asList(medicine, medicine2);
        // mock responses
        when(medicineClient.findAllMedicines()).thenReturn(medicines);
        when(medicineClient.findMedicineById(1l)).thenReturn(medicine);
        when(medicineClient.findMedicineByName("Ibuprofeno")).thenReturn(Optional.of(medicine));

        Medicine medicine = new Medicine("Ibuprofeno", 18, true, new Money(new BigDecimal("3.45")));
        warehouseMedicine = new WarehouseMedicine(medicine);
        warehouseMedicine.setId(1l);
        warehouseMedicine2 = new WarehouseMedicine("Paracetamol", 20, true, new Money(new BigDecimal("3.85")), new Money(new BigDecimal("3.95")));
        warehouseMedicine2.setId(2l);
        List<WarehouseMedicine> warehouseMedicines = Arrays.asList(warehouseMedicine, warehouseMedicine2);
        when(medicineClient.findAllWarehouseMedicines()).thenReturn(warehouseMedicines);
        when(medicineClient.findWarehouseMedicineById(warehouseMedicine.getId())).thenReturn(warehouseMedicine);
        WarehouseMedicineQuantityVM vm = new WarehouseMedicineQuantityVM("Ibuprofeno", 1);
        when(medicineClient.findQuantityByName("Ibuprofeno")).thenReturn(Optional.of(vm));
        when(medicineClient.findMedicineById(2l)).thenReturn(medicine2);
        when(medicineClient.findWarehouseMedicineByName("Ibuprofeno")).thenReturn(Optional.of(Collections.singletonList(warehouseMedicine)));
        doAnswer(i -> {
            return null;
        }).when(medicineClient).addWarehouseMedicines(medicine.getId(), 1);
        doAnswer(i -> {
            return null;
        }).when(medicineClient).addWarehouseMedicines(medicine2.getId(), 1);
        doAnswer(i -> {
            return null;
        }).when(medicineClient).deleteWarehouseMedicine(warehouseMedicine.getId());
    }

    @Test
    @DisplayName("Unit test - retrieval of all medicines")
    void findAllMedicines() {
        List<Medicine> medicineList = medicineService.findAllMedicines();
        assertEquals(2, medicineList.size());
    }

    @Test
    void errorFindAllMedicines() {
        assertThrows(MedicineServiceDownException.class, () -> medicineService.errorFindAllMedicines());
    }

    @Test
    @DisplayName("Unit test - retrieval of a medicine with specific id")
    void findMedicineById() {
        assertEquals("Ibuprofeno", medicineService.findMedicineById(1l).getName());
    }

    @Test
    void errorFindMedicineById() {
        assertThrows(MedicineServiceDownException.class, () -> medicineService.errorFindMedicineById(null));
    }

    @Test
    @DisplayName("Unit test - retrieval of a medicine by name")
    void findMedicineByName() {
        assertEquals("Ibuprofeno", medicineService.findMedicineByName("Ibuprofeno").get().getName());
    }

    @Test
    void errorFindMedicineByName() {
        assertThrows(MedicineServiceDownException.class, () -> medicineService.errorFindMedicineByName(null));
    }

    @Test
    @DisplayName("Unit test - retrieval of all warehouse-medicines")
    void findAllWarehouseMedicines() {
        List<WarehouseMedicine> warehouseMedicines = medicineService.findAllWarehouseMedicines();
        assertEquals(2, warehouseMedicines.size());
    }

    @Test
    void errorFindAllWarehouseMedicines() {
        assertThrows(MedicineServiceDownException.class, () -> medicineService.errorFindAllWarehouseMedicines());
    }

    @Test
    @DisplayName("Unit test - retrieval of a warehouse-medicine with specific id")
    void findWarehouseMedicineById() {
        assertEquals("Ibuprofeno", medicineService.findWarehouseMedicineById(1l).getName());
    }

    @Test
    void errorFindWarehouseMedicineById() {
        assertThrows(MedicineServiceDownException.class, () -> medicineService.errorFindWarehouseMedicineById(null));
    }

    @Test
    @DisplayName("Unit test - retrieval of a warehouse-medicine by name")
    void findWarehouseMedicineByName() {
        assertEquals("Ibuprofeno", medicineService.findWarehouseMedicineByName("Ibuprofeno").get().get(0).getName());
    }

    @Test
    void errorFindWarehouseMedicineByName() {
        assertThrows(MedicineServiceDownException.class, () -> medicineService.errorFindWarehouseMedicineByName(null));
    }

    @Test
    @DisplayName("Unit test - retrieval of a warehouse-medicine quantity by name")
    void findQuantityByName() {
        assertEquals("Ibuprofeno", medicineService.findQuantityByName("Ibuprofeno").get().getName());
    }

    @Test
    void errorFindQuantityByName() {
        assertThrows(MedicineServiceDownException.class, () -> medicineService.errorFindQuantityByName(null));
    }

    @Test
    @DisplayName("Unit test - add quantity of a warehouse-medicine ")
    void addWarehouseMedicines() throws Exception {
        medicineService.addWarehouseMedicines(warehouseMedicine.getId(), 5);
    }

    @Test
    void errorAddWarehouseMedicines() {
        assertThrows(MedicineServiceDownException.class, () -> medicineService.errorAddWarehouseMedicines(null, null));
    }

    @Test
    @DisplayName("Unit test - update price of a warehouse-medicine ")
    void updatePrice() throws Exception {
        medicineService.updatePrice(warehouseMedicine.getId(), "10");
    }

    @Test
    void errorUpdatePrice() {
        assertThrows(MedicineServiceDownException.class, () -> medicineService.errorUpdatePrice(null, null));
    }

    @Test
    @DisplayName("Unit test - remove a warehouse-medicine ")
    void deleteWarehouseMedicine() throws Exception {
        medicineService.deleteWarehouseMedicine(warehouseMedicine.getId());
    }

    @Test
    void errorDeleteWarehouseMedicine() {
        assertThrows(MedicineServiceDownException.class, () -> medicineService.errorDeleteWarehouseMedicine(null));
    }
}