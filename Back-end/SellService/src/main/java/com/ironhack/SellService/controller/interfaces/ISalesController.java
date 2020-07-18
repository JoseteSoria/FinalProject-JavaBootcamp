package com.ironhack.SellService.controller.interfaces;

import com.ironhack.SellService.model.Sales;

import java.util.List;

public interface ISalesController {
    public List<Sales> findAll();

    public Sales findById(Long id);

    public Sales findLastOrder();

    public Sales create(Sales sales);
}
