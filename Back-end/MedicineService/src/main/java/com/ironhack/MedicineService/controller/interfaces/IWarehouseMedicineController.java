package com.ironhack.MedicineService.controller.interfaces;

import com.ironhack.MedicineService.model.WarehouseMedicine;
import com.ironhack.MedicineService.model.viewModel.WarehouseMedicineQuantityVM;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IWarehouseMedicineController {

    public List<WarehouseMedicine> findAllWarehouseMedicines();

    public WarehouseMedicine findWarehouseMedicineById(Long id);

    public Optional<WarehouseMedicineQuantityVM> findQuantityByName(String name);

    public Optional<List<WarehouseMedicine>> findWarehouseMedicineByName(String name);

    public void addWarehouseMedicines(Long id, Integer quantity);

    public void updatePrice(Long id, String price);

    public void deleteWarehouseMedicine(Long id);
}
