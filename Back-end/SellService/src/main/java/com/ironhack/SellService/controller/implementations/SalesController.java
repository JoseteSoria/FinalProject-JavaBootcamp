package com.ironhack.SellService.controller.implementations;

import com.ironhack.SellService.controller.interfaces.ISalesController;
import com.ironhack.SellService.model.Sales;
import com.ironhack.SellService.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SalesController implements ISalesController {
    @Autowired
    private SalesService salesService;

    @GetMapping("/sales")
    @ResponseStatus(HttpStatus.OK)
    public List<Sales> findAllSales() {
        return salesService.findAll();
    }

    @GetMapping("/sales/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Sales findSaleById(@PathVariable Long id) {
        return salesService.findById(id);
    }

    @GetMapping("/sales/last")
    @ResponseStatus(HttpStatus.OK)
    public Sales findLastSale() {
        return salesService.findLastSale();
    }

    @PostMapping("/sales")
    @ResponseStatus(HttpStatus.CREATED)
    public Sales createSale(@RequestBody @Valid Sales sales) {
        return salesService.store(sales);
    }
}
