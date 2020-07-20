package com.ironhack.MedicineService.controller.interfaces;

import com.ironhack.MedicineService.model.WarehouseMedicine;
import com.ironhack.MedicineService.model.dto.MedicinesToSellDTO;
import com.ironhack.MedicineService.model.dto.MedicinesToStoreDTO;
import com.ironhack.MedicineService.model.viewModel.WarehouseMedicineQuantityVM;

import java.util.List;
import java.util.Optional;

public interface IWarehouseMedicineController {

    public List<WarehouseMedicine> findAllWarehouseMedicines();

    public WarehouseMedicine findWarehouseMedicineById(Long id);

    public Optional<WarehouseMedicineQuantityVM> findQuantityByName(String name);

    public Optional<List<WarehouseMedicine>> findWarehouseMedicineByName(String name);

    public void addWarehouseMedicines(List<MedicinesToStoreDTO> medicinesToStoreDTOS);

    public void updatePrice(Long id, String price);

    public void deleteWarehouseMedicine(Long id);

    public void removeWarehouseMedicineMultiple(List<MedicinesToSellDTO> medicinesToSellDTOS);
}
