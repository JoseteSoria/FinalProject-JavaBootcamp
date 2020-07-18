package com.ironhack.SellService.controller.interfaces;

import com.ironhack.SellService.model.MedicineSold;

import java.util.List;

public interface IMedicineSoldController {
    public List<MedicineSold> findAll();

    public MedicineSold findById(Long id);

    public List<MedicineSold> findBySalesId(Long id);

    public List<MedicineSold> create(List<MedicineSold> medicines);
}
