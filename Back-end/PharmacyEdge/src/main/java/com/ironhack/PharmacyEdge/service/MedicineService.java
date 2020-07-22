package com.ironhack.PharmacyEdge.service;

import com.ironhack.PharmacyEdge.client.MedicineClient;
import com.ironhack.PharmacyEdge.exceptions.MedicineServiceDownException;
import com.ironhack.PharmacyEdge.model.medicine.Medicine;
import com.ironhack.PharmacyEdge.model.medicine.WarehouseMedicine;
import com.ironhack.PharmacyEdge.model.medicine.viewModel.WarehouseMedicineQuantityVM;
import com.ironhack.PharmacyEdge.model.order.dto.MedicinesToStoreDTO;
import com.ironhack.PharmacyEdge.model.sell.dto.MedicinesToSellDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {
    private static final Logger LOGGER = LogManager.getLogger(MedicineService.class);

    @Autowired
    MedicineClient medicineClient;

    @HystrixCommand(fallbackMethod = "errorFindAllMedicines")
    public List<Medicine> findAllMedicines() {
        LOGGER.info("GET request to retrieve all medicines");
        return medicineClient.findAllMedicines();
    }

    public List<Medicine> errorFindAllMedicines() {
        LOGGER.error("Controlled exception - fail in GET request to retrieve all the medicines. ");
        throw new MedicineServiceDownException(" Medicine Service Down. Method findAllMedicines. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindMedicineById")
    public Medicine findMedicineById(Long id) {
        LOGGER.info("GET request to retrieve medicine by id. Id: " + id);
        return medicineClient.findMedicineById(id);
    }

    public Medicine errorFindMedicineById(Long id) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve medicine by id. ");
        throw new MedicineServiceDownException(" Medicine Service Down. Method findMedicineById. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindMedicineByName")
    public Optional<Medicine> findMedicineByName(String name) {
        LOGGER.info("GET request to retrieve medicine by name. Name: " + name);
        return medicineClient.findMedicineByName(name);
    }

    public Optional<Medicine> errorFindMedicineByName(String name) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve medicine by name. ");
        throw new MedicineServiceDownException(" Medicine Service Down. Method errorFindMedicineByName. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindAllWarehouseMedicines")
    public List<WarehouseMedicine> findAllWarehouseMedicines() {
        LOGGER.info("GET request to retrieve all the medicines in Warehouse. ");
        return medicineClient.findAllWarehouseMedicines();
    }

    public List<WarehouseMedicine> errorFindAllWarehouseMedicines() {
        LOGGER.error("Controlled exception - fail in GET request to retrieve all medicines in Warehouse. ");
        throw new MedicineServiceDownException(" Medicine Service Down. Method findAllWarehouseMedicines. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindWarehouseMedicineById")
    public WarehouseMedicine findWarehouseMedicineById(Long id) {
        LOGGER.info("GET request to retrieve warehouse-medicine by id. Id: " + id);
        return medicineClient.findWarehouseMedicineById(id);
    }

    public WarehouseMedicine errorFindWarehouseMedicineById(Long id) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve warehouse-medicine by id. ");
        throw new MedicineServiceDownException(" Medicine Service Down. Method findWarehouseMedicineById. ");
    }

    @HystrixCommand(fallbackMethod = "errorAddWarehouseMedicines")
    public void addWarehouseMedicines(List<MedicinesToStoreDTO> medicinesToStoreDTOS) {
        LOGGER.info("POST request to add warehouse-medicine by medicineId and quantity. ");
        medicineClient.addWarehouseMedicines(medicinesToStoreDTOS);
    }

    public void errorAddWarehouseMedicines(List<MedicinesToStoreDTO> medicinesToStoreDTOS) {
        LOGGER.error("Controlled exception - fail in POST request to add warehouse-medicine by medicineId and quantity. ");
        throw new MedicineServiceDownException(" Medicine Service Down. Method findWarehouseMedicineById. ");
    }

    @HystrixCommand(fallbackMethod = "errorUpdatePrice")
    public void updatePrice(Long warehouseMedicineId, String newPrice) {
        LOGGER.info("PUT request to update warehouse-medicine price by id. ");
        medicineClient.updatePrice(warehouseMedicineId, newPrice);
    }

    public void errorUpdatePrice(Long warehouseMedicineId, String newPrice) {
        LOGGER.error("Controlled exception - fail in PUT request to update de price. ");
        throw new MedicineServiceDownException(" Medicine Service Down. Method updatePrice. ");
    }

    @HystrixCommand(fallbackMethod = "errorDeleteWarehouseMedicine")
    public void deleteWarehouseMedicine(Long id) {
        LOGGER.info("DELETE request to remove warehouse-medicine by id. ");
        medicineClient.deleteWarehouseMedicine(id);
    }

    public void errorDeleteWarehouseMedicine(Long id) {
        LOGGER.error("Controlled exception - fail in DELETE request to remove a warehouse-medicine. ");
        throw new MedicineServiceDownException(" Medicine Service Down. Method deleteWarehouseMedicine. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindQuantityByName")
    public Optional<WarehouseMedicineQuantityVM> findQuantityByName(String name) {
        LOGGER.info("GET request to retrieve quantities by warehouse-medicine name. ");
        return medicineClient.findQuantityByName(name);
    }

    public Optional<WarehouseMedicineQuantityVM> errorFindQuantityByName(String name) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve quantities by warehouse-medicine name. ");
        throw new MedicineServiceDownException(" Medicine Service Down. Method findQuantityByName. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindWarehouseMedicineByName")
    public Optional<List<WarehouseMedicine>> findWarehouseMedicineByName(String name) {
        LOGGER.info("GET request to retrieve medicines by name. ");
        return medicineClient.findWarehouseMedicineByName(name);
    }

    public Optional<List<WarehouseMedicine>> errorFindWarehouseMedicineByName(String name) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve medicines by name. ");
        throw new MedicineServiceDownException(" Medicine Service Down. Method findWarehouseMedicineByName. ");
    }

    @HystrixCommand(fallbackMethod = "errorFindMedicinesCloseToExpirationDate")
    public Optional<List<WarehouseMedicine>> findMedicinesCloseToExpirationDate(Integer months) {
        LOGGER.info("GET request to retrieve warehouse-medicines with less than " + months + " months to expiration date");
        return medicineClient.findMedicinesCloseToExpirationDate(months);
    }

    public Optional<List<WarehouseMedicine>> errorFindMedicinesCloseToExpirationDate(Integer months) {
        LOGGER.error("Controlled exception - fail in GET request to retrieve warehouse-medicines close to expiration. ");
        throw new MedicineServiceDownException(" Medicine Service Down. Method findMedicinesCloseToExpirationDate. ");
    }

    @HystrixCommand(fallbackMethod = "errorRemoveWarehouseMedicinesMultiple")
    public void removeWarehouseMedicinesMultiple(List<MedicinesToSellDTO> medicinesToSellDTOS){
        LOGGER.info("DELETE request to remove several medicines. ");
        medicineClient.removeWarehouseMedicineMultiple(medicinesToSellDTOS);
    }

    public void errorRemoveWarehouseMedicinesMultiple(List<MedicinesToSellDTO> medicinesToSellDTOS){
        LOGGER.error("Controlled exception - fail in DELETE request to remove several medicines. ");
        throw new MedicineServiceDownException(" Medicine Service Down. Method removeWarehouseMedicineMultiple. ");
    }

}
