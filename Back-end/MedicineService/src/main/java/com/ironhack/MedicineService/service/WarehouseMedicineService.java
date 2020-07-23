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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseMedicineService {
    private static final Logger LOGGER = LogManager.getLogger(WarehouseMedicineService.class);

    @Autowired
    private WarehouseMedicineRepository warehouseMedicineRepository;
    @Autowired
    private MedicineRepository medicineRepository;

    public List<WarehouseMedicine> findAll() {
        LOGGER.info("GET request to retrieve all warehouse-medicines");
        return warehouseMedicineRepository.findAll();
    }

    public WarehouseMedicine findById(Long id) {
        LOGGER.info("GET request to retrieve warehouse-medicine by id. Id: " + id);
        return warehouseMedicineRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Warehouse medicine not found with that id"));
    }

    public void addWarehouseMedicines(Long medicineId, Integer quantity) {
        LOGGER.info("Add warehouse medicines. Medicine Id: " + medicineId + ". Quantity: " + quantity);
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
        LOGGER.info("Update warehouse medicine price. Warehouse medicine Id: " + warehouseMedicineId + ". NewPrice: " + newPrice);
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
        LOGGER.info("Delete warehouse medicine price. Warehouse medicine Id: " + id);
        WarehouseMedicine warehouseMedicine = findById(id);
        warehouseMedicineRepository.delete(warehouseMedicine);
    }

    public Optional<WarehouseMedicineQuantityVM> findQuantityByName(String name) {
        LOGGER.info("GET request to retrieve warehouse-medicine by name. Name: " + name);
        Optional<Object[]> obj = warehouseMedicineRepository.findMedicineQuantityByName(name);
        if (!obj.isEmpty()) {
            WarehouseMedicineQuantityVM vm = (WarehouseMedicineQuantityVM) (obj.get()[0]);
            return Optional.of(new WarehouseMedicineQuantityVM(vm.getName(), vm.getQuantity()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<List<WarehouseMedicine>> findByName(String name) {
        LOGGER.info("GET request to retrieve all warehouse-medicine by name. Name: " + name);
        return warehouseMedicineRepository.findAllMedicinesByName(name);
    }

    @Transactional
    public void addWarehouseMedicinesMultiple(List<MedicinesToStoreDTO> medicinesToStoreDTOS) {
        LOGGER.info("GET request to add warehouse-medicines multiple times.");
        for(MedicinesToStoreDTO medicinesToStoreDTO: medicinesToStoreDTOS){
            addWarehouseMedicines(medicinesToStoreDTO.getMedicineId(), medicinesToStoreDTO.getQuantity());
        }
    }

    @Transactional
    public void removeWarehouseMedicinesMultiple(List<MedicinesToSellDTO> medicinesToSellDTOS) {
        LOGGER.info("GET request to remove warehouse-medicines multiple times.");
        for(MedicinesToSellDTO medicinesToSellDTO: medicinesToSellDTOS){
            delete(medicinesToSellDTO.getWarehouseMedicineId());
        }
    }

    public Optional<List<WarehouseMedicine>> findMedicinesCloseToExpirationDate(Integer months){
        LOGGER.info("GET request to retrieve warehouse-medicine with less than " + months + " months to expiry date");
        Calendar cal = Calendar.getInstance();
        if(months < 12) cal.add(Calendar.MONTH, months);
        else {
            cal.add(Calendar.YEAR, months/12);
            cal.add(Calendar.MONTH, months%12);
        }
        return warehouseMedicineRepository.findMedicinesCloseToExpirationDate(cal);
    }
}
