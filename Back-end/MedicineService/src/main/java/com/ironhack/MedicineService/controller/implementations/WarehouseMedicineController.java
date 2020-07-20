package com.ironhack.MedicineService.controller.implementations;

import com.ironhack.MedicineService.controller.interfaces.IWarehouseMedicineController;
import com.ironhack.MedicineService.model.WarehouseMedicine;
import com.ironhack.MedicineService.model.dto.MedicinesToStoreDTO;
import com.ironhack.MedicineService.model.viewModel.WarehouseMedicineQuantityVM;
import com.ironhack.MedicineService.service.WarehouseMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class WarehouseMedicineController implements IWarehouseMedicineController {
    @Autowired
    private WarehouseMedicineService warehouseMedicineService;

    @GetMapping("/warehouse-medicines")
    @ResponseStatus(HttpStatus.OK)
    public List<WarehouseMedicine> findAllWarehouseMedicines() {
        return warehouseMedicineService.findAll();
    }

    @GetMapping("/warehouse-medicines/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WarehouseMedicine findWarehouseMedicineById(@PathVariable Long id) {
        return warehouseMedicineService.findById(id);
    }

    @GetMapping("/warehouse-medicines/name/{name}/quantity")
    @ResponseStatus(HttpStatus.OK)
    public Optional<WarehouseMedicineQuantityVM> findQuantityByName(@PathVariable(name = "name") String name) {
        return warehouseMedicineService.findQuantityByName(name);
    }

    @GetMapping("/warehouse-medicines/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<List<WarehouseMedicine>> findWarehouseMedicineByName(@PathVariable(name = "name") String name) {
        return warehouseMedicineService.findByName(name);
    }

//    Deprecated
//    @PostMapping("/warehouse-medicines/{id}/add/{quantity}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addWarehouseMedicineOnce(@PathVariable(name = "id") Long id, @PathVariable(name = "quantity") Integer quantity) {
//        warehouseMedicineService.addWarehouseMedicines(id, quantity);
//    }

    @PostMapping("/warehouse-medicines/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addWarehouseMedicines(@RequestBody List<MedicinesToStoreDTO> medicinesToStoreDTOS) {
        warehouseMedicineService.addWarehouseMedicinesMultiple(medicinesToStoreDTOS);
    }

    @PutMapping("/warehouse-medicines/{id}/update-price/{price}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePrice(@PathVariable(name = "id") Long id, @PathVariable(name = "price") String price) {
        warehouseMedicineService.updatePriceByNameId(id, price);
    }

    @DeleteMapping("/warehouse-medicines/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWarehouseMedicine(@PathVariable(name = "id") Long id) {
        warehouseMedicineService.delete(id);
    }
}
