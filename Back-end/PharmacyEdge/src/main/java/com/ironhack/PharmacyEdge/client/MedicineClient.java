package com.ironhack.PharmacyEdge.client;

import com.ironhack.PharmacyEdge.model.medicine.Medicine;
import com.ironhack.PharmacyEdge.model.medicine.WarehouseMedicine;
import com.ironhack.PharmacyEdge.model.medicine.viewModel.WarehouseMedicineQuantityVM;
import com.ironhack.PharmacyEdge.model.order.dto.MedicinesToStoreDTO;
import com.ironhack.PharmacyEdge.model.sell.dto.MedicinesToSellDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "medicine-service")
public interface MedicineClient {
    @GetMapping("/medicines")
    public List<Medicine> findAllMedicines();

    @GetMapping("/medicines/{id}")
    public Medicine findMedicineById(@PathVariable Long id);

    @GetMapping("/medicines/name/{name}")
    public Optional<Medicine> findMedicineByName(@PathVariable(name = "name") String name);

    @GetMapping("/warehouse-medicines")
    public List<WarehouseMedicine> findAllWarehouseMedicines();

    @GetMapping("/warehouse-medicines/{id}")
    public WarehouseMedicine findWarehouseMedicineById(@PathVariable Long id);

    @GetMapping("/warehouse-medicines/name/{name}/quantity")
    public Optional<WarehouseMedicineQuantityVM> findQuantityByName(@PathVariable(name = "name") String name);

    @GetMapping("/warehouse-medicines/name/{name}")
    public Optional<List<WarehouseMedicine>> findWarehouseMedicineByName(@PathVariable(name = "name") String name);

    @GetMapping("/warehouse-medicines/near-expiration/{months}")
    public Optional<List<WarehouseMedicine>> findMedicinesCloseToExpirationDate(@PathVariable(name = "months") Integer months);

    @PostMapping("/warehouse-medicines/add")
    public void addWarehouseMedicines(@RequestBody List<MedicinesToStoreDTO> medicinesToStoreDTOS);

    @PutMapping("/warehouse-medicines/{id}/update-price/{price}")
    public void updatePrice(@PathVariable(name = "id") Long id, @PathVariable(name = "price") String price);

    @DeleteMapping("/warehouse-medicines/delete/{id}")
    public void deleteWarehouseMedicine(@PathVariable(name = "id") Long id);

    @DeleteMapping("/warehouse-medicines/delete")
    public void removeWarehouseMedicineMultiple(@RequestBody List<MedicinesToSellDTO> medicinesToSellDTOS);
}