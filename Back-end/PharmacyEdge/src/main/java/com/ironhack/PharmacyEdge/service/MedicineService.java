package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.client.MedicineClient;
import com.ironhack.PharmacyEdge.model.medicine.Medicine;
import com.ironhack.PharmacyEdge.model.medicine.WarehouseMedicine;
import com.ironhack.PharmacyEdge.model.medicine.viewModel.WarehouseMedicineQuantityVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {
    @Autowired
    MedicineClient medicineClient;

    public List<Medicine> findAllMedicines() {
        return medicineClient.findAllMedicines();
    }

    public Medicine findMedicineById(Long id) {
        return medicineClient.findMedicineById(id);
    }

    public Optional<Medicine> findMedicineByName(String name) {
        return medicineClient.findMedicineByName(name);
    }

    public List<WarehouseMedicine> findAllWarehouseMedicines() {
        return medicineClient.findAllWarehouseMedicines();
    }

    public WarehouseMedicine findWarehouseMedicineById(Long id) {
        return medicineClient.findWarehouseMedicineById(id);
    }

    public void addWarehouseMedicines(Long medicineId, Integer quantity) {
        medicineClient.addWarehouseMedicines(medicineId, quantity);
    }

    public void updatePrice(Long warehouseMedicineId, String newPrice) {
        medicineClient.updatePrice(warehouseMedicineId, newPrice);
    }

    public void deleteWarehouseMedicine(Long id) {
        medicineClient.deleteWarehouseMedicine(id);
    }

    public Optional<WarehouseMedicineQuantityVM> findQuantityByName(String name) {
        return medicineClient.findQuantityByName(name);
    }

    public Optional<List<WarehouseMedicine>> findWarehouseMedicineByName(String name) {
        return medicineClient.findWarehouseMedicineByName(name);
    }
}
