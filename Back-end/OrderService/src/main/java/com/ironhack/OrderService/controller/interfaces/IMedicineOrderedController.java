package com.ironhack.OrderService.controller.interfaces;

import com.ironhack.OrderService.model.MedicineOrdered;

import java.util.List;

public interface IMedicineOrderedController {
    public List<MedicineOrdered> findAllMedicinesOrdered();

    public MedicineOrdered findMedicineOrderedById(Long id);

    public List<MedicineOrdered> findMedicineOrderedByOrderId(Long id);

    public List<MedicineOrdered> createMedicineOrdered(List<MedicineOrdered> medicines);
}
