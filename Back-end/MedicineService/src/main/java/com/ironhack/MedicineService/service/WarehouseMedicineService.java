package com.ironhack.MedicineService.service;

import com.ironhack.MedicineService.classes.Money;
import com.ironhack.MedicineService.exceptions.IllegalInputException;
import com.ironhack.MedicineService.exceptions.ResourceNotFoundException;
import com.ironhack.MedicineService.model.Medicine;
import com.ironhack.MedicineService.model.WarehouseMedicine;
import com.ironhack.MedicineService.repository.MedicineRepository;
import com.ironhack.MedicineService.repository.WarehouseMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseMedicineService {
    @Autowired
    private WarehouseMedicineRepository warehouseMedicineRepository;
    @Autowired
    private MedicineRepository medicineRepository;

    public List<WarehouseMedicine> findAll(){
        return warehouseMedicineRepository.findAll();
    }

    public WarehouseMedicine findById(Long id){
        return warehouseMedicineRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Warehouse medicine not found with that id"));
    }

    public WarehouseMedicine newStore(Long medicineId){
        Medicine medicine = medicineRepository.findById(medicineId).orElseThrow(
                ()->new ResourceNotFoundException("Medicine not found with that id"));
        Optional<WarehouseMedicine> warehouseMedicine = findByName(medicine.getName());
        if(!warehouseMedicine.isEmpty()){
            throw new IllegalInputException("That medicine is already in our database");
        }
        return warehouseMedicineRepository.save(new WarehouseMedicine(medicine,0));
    }

    public void addQuantity(Long warehouseMedicineId, Integer quantity){
        WarehouseMedicine warehouseMedicine = findById(warehouseMedicineId);
        warehouseMedicine.setQuantity(warehouseMedicine.getQuantity() + quantity);
        warehouseMedicineRepository.save(warehouseMedicine);
    }

    public void reduceQuantity(Long warehouseMedicineId, Integer quantity){
        WarehouseMedicine warehouseMedicine = findById(warehouseMedicineId);
        if (quantity > warehouseMedicine.getQuantity()){
            throw new IllegalArgumentException("There are not so many medicines of that type");
        }
        warehouseMedicine.setQuantity(warehouseMedicine.getQuantity() - quantity);
        warehouseMedicineRepository.save(warehouseMedicine);
    }

    public void updatePrice(Long warehouseMedicineId, BigDecimal newPrice){
        WarehouseMedicine warehouseMedicine = findById(warehouseMedicineId);
        if (newPrice.compareTo(warehouseMedicine.getMinimumPrice().getAmount()) < 0){
            warehouseMedicine.setPrice(warehouseMedicine.getMinimumPrice());
        }
        else{
            warehouseMedicine.setPrice(new Money(newPrice));
        }
        warehouseMedicineRepository.save(warehouseMedicine);
    }

    public void delete(Long id){
        WarehouseMedicine warehouseMedicine = findById(id);
        warehouseMedicineRepository.delete(warehouseMedicine);
    }

    public Optional<WarehouseMedicine> findByName(String name){
        return warehouseMedicineRepository.findByName(name);
    }
}
