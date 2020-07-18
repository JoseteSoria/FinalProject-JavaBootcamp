package com.ironhack.MedicineService.controller.interfaces;

import com.ironhack.MedicineService.model.WarehouseMedicine;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IWarehouseMedicineController {

    public List<WarehouseMedicine> findAll();

    public WarehouseMedicine findById(Long id);

    public Optional<WarehouseMedicine> findByUsername(String name);

    public void addQuantity(Long id, Integer quantity);

    public void reduceQuantity(Long id, Integer quantity);

    public void updatePrice(Long id, BigDecimal price);

    public WarehouseMedicine createWarehouseMedicine(Long id);

    public void deleteWarehouseMedicine(Long id);
}
