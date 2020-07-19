package com.ironhack.PharmacyEdge.controller.interfaces;

import com.ironhack.PharmacyEdge.model.medicine.Medicine;
import com.ironhack.PharmacyEdge.model.medicine.WarehouseMedicine;
import com.ironhack.PharmacyEdge.model.medicine.viewModel.WarehouseMedicineQuantityVM;

import java.util.List;
import java.util.Optional;

public interface IMedicineController {
    public List<Medicine> findAllMedicines();

    public Medicine findMedicineById(Long id);

    public Optional<Medicine> findMedicineByName(String name);

    public List<WarehouseMedicine> findAllWarehouseMedicines();

    public WarehouseMedicine findWarehouseMedicineById(Long id);

    public Optional<WarehouseMedicineQuantityVM> findQuantityByName(String name);

    public Optional<List<WarehouseMedicine>> findWarehouseMedicineByName(String name);

    public void addWarehouseMedicines(Long id, Integer quantity);

    public void updatePrice(Long id, String price);

    public void deleteWarehouseMedicine(Long id);
}
