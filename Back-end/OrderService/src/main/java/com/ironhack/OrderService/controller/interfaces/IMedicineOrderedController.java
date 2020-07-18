package com.ironhack.OrderService.controller.interfaces;

import com.ironhack.OrderService.model.MedicineOrdered;

import java.util.List;

public interface IMedicineOrderedController {
    public List<MedicineOrdered> findAll();

    public MedicineOrdered findById(Long id);

    public List<MedicineOrdered> findByOrderId(Long id);

    public List<MedicineOrdered> create(List<MedicineOrdered> medicines);
}
