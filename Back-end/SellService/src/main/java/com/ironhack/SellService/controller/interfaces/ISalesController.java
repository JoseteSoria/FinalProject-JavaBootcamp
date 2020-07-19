package com.ironhack.SellService.controller.interfaces;

import com.ironhack.SellService.model.Sales;

import java.util.List;

public interface ISalesController {
    public List<Sales> findAllSales();

    public Sales findSaleById(Long id);

    public Sales findLastSale();

    public Sales createSale(Sales sales);
}
