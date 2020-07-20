package com.ironhack.MedicineService.service;

import com.ironhack.MedicineService.classes.Money;
import com.ironhack.MedicineService.exceptions.ResourceNotFoundException;
import com.ironhack.MedicineService.model.Medicine;
import com.ironhack.MedicineService.model.WarehouseMedicine;
import com.ironhack.MedicineService.model.dto.MedicinesToSellDTO;
import com.ironhack.MedicineService.model.dto.MedicinesToStoreDTO;
import com.ironhack.MedicineService.model.viewModel.WarehouseMedicineQuantityVM;
import com.ironhack.MedicineService.repository.MedicineRepository;
import com.ironhack.MedicineService.repository.WarehouseMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseMedicineService {
    @Autowired
    private WarehouseMedicineRepository warehouseMedicineRepository;
    @Autowired
    private MedicineRepository medicineRepository;

    public List<WarehouseMedicine> findAll() {
        return warehouseMedicineRepository.findAll();
    }

    public WarehouseMedicine findById(Long id) {
        return warehouseMedicineRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Warehouse medicine not found with that id"));
    }

    public void addWarehouseMedicines(Long medicineId, Integer quantity) {
        Medicine medicine = medicineRepository.findById(medicineId).orElseThrow(
                () -> new ResourceNotFoundException("Warehouse medicine not found with that id"));
        List<WarehouseMedicine> warehouseMedicineList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            WarehouseMedicine warehouseMedicine = new WarehouseMedicine(medicine);
            warehouseMedicineList.add((warehouseMedicine));
        }
        // The following line does not work because is the same reference object n times and
        // because of that Hibernate only persist once the data
        // List<WarehouseMedicine> warehouseMedicineList = Collections.nCopies(5, warehouseMedicine);
        warehouseMedicineRepository.saveAll(warehouseMedicineList);
    }

    public void updatePriceByNameId(Long warehouseMedicineId, String newPrice) {
        WarehouseMedicine warehouseMedicine = findById(warehouseMedicineId);
        List<WarehouseMedicine> warehouseMedicines = findByName(warehouseMedicine.getName()).orElseThrow(
                () -> new ResourceNotFoundException("Warehouse medicine not found with that id/name"));
        ;
        if (new BigDecimal(newPrice).compareTo(warehouseMedicine.getMinimumPrice().getAmount()) < 0) {
            for (WarehouseMedicine medicine : warehouseMedicines) {
                medicine.setPrice(warehouseMedicine.getMinimumPrice());
            }
        } else {
            for (WarehouseMedicine medicine : warehouseMedicines) {
                medicine.setPrice(new Money(new BigDecimal(newPrice)));
            }
        }
        warehouseMedicineRepository.saveAll(warehouseMedicines);
    }

    public void delete(Long id) {
        WarehouseMedicine warehouseMedicine = findById(id);
        warehouseMedicineRepository.delete(warehouseMedicine);
    }

    public Optional<WarehouseMedicineQuantityVM> findQuantityByName(String name) {
        Optional<Object[]> obj = warehouseMedicineRepository.findMedicineQuantityByName(name);
        if (!obj.isEmpty()) {
            WarehouseMedicineQuantityVM vm = (WarehouseMedicineQuantityVM) (obj.get()[0]);
            return Optional.of(new WarehouseMedicineQuantityVM(vm.getName(), vm.getQuantity()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<List<WarehouseMedicine>> findByName(String name) {
        return warehouseMedicineRepository.findAllMedicinesByName(name);
    }

    @Transactional
    public void addWarehouseMedicinesMultiple(List<MedicinesToStoreDTO> medicinesToStoreDTOS) {
        for(MedicinesToStoreDTO medicinesToStoreDTO: medicinesToStoreDTOS){
            addWarehouseMedicines(medicinesToStoreDTO.getMedicineId(), medicinesToStoreDTO.getQuantity());
        }
    }

    @Transactional
    public void removeWarehouseMedicinesMultiple(List<MedicinesToSellDTO> medicinesToSellDTOS) {
        for(MedicinesToSellDTO medicinesToSellDTO: medicinesToSellDTOS){
            delete(medicinesToSellDTO.getWarehouseMedicineId());
        }
    }
}
