package com.ironhack.MedicineService.controller.implementations;

import com.ironhack.MedicineService.controller.interfaces.IWarehouseMedicineController;
import com.ironhack.MedicineService.model.WarehouseMedicine;
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
    public List<WarehouseMedicine> findAll() {
        return warehouseMedicineService.findAll();
    }

    @GetMapping("/warehouse-medicines/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WarehouseMedicine findById(@PathVariable Long id) {
        return warehouseMedicineService.findById(id);
    }

    @GetMapping( "/warehouse-medicines/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<WarehouseMedicine> findByUsername(@PathVariable(name = "name") String name) {
        return warehouseMedicineService.findByName(name);
    }

    @PutMapping("/warehouse-medicines/{id}/add/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addQuantity(@PathVariable(name = "id") Long id, @PathVariable(name = "quantity") Integer quantity){
        warehouseMedicineService.addQuantity(id, quantity);
    }

    @PutMapping("/warehouse-medicines/{id}/reduce/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reduceQuantity(@PathVariable(name = "id") Long id, @PathVariable(name = "quantity") Integer quantity){
        warehouseMedicineService.reduceQuantity(id, quantity);
    }

    @PutMapping("/warehouse-medicines/{id}/update-price/{price}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePrice(@PathVariable(name = "id") Long id, @PathVariable(name = "price") BigDecimal price){
        warehouseMedicineService.updatePrice(id, price);
    }

    @PostMapping("/warehouse-medicines/create/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public WarehouseMedicine createWarehouseMedicine(@PathVariable(name = "id") Long id){
        return warehouseMedicineService.newStore(id);
    }

    @DeleteMapping("/warehouse-medicines/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWarehouseMedicine(@PathVariable(name = "id") Long id){
        warehouseMedicineService.delete(id);
    }
}
