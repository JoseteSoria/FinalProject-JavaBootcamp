package com.ironhack.SellService.controller.interfaces;

import com.ironhack.SellService.model.MedicineSold;

import java.util.List;

public interface IMedicineSoldController {
    public List<MedicineSold> findAllMedicinesSold();

    public MedicineSold findMedicineSoldById(Long id);

    public List<MedicineSold> findMedicineSoldBySalesId(Long id);

    public List<MedicineSold> createMedicineSold(List<MedicineSold> medicines);
}
