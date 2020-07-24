package com.ironhack.PharmacyEdge.controller.implementations;

import com.ironhack.PharmacyEdge.controller.interfaces.ISellController;
import com.ironhack.PharmacyEdge.model.sell.MedicineSold;
import com.ironhack.PharmacyEdge.model.sell.Sales;
import com.ironhack.PharmacyEdge.model.sell.dto.MedicinesToSellDTO;
import com.ironhack.PharmacyEdge.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SellController implements ISellController {
    @Autowired
    SellService sellService;

    @GetMapping("/sales")
    @ResponseStatus(HttpStatus.OK)
    public List<Sales> findAllSales(){
        return sellService.findAllSales();
    }

    @GetMapping("/sales/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Sales findSaleById(@PathVariable Long id){
        return sellService.findSaleById(id);
    }

    @GetMapping("/sales/last")
    @ResponseStatus(HttpStatus.OK)
    public Sales findLastSale(){
        return sellService.findLastSale();
    }

    @PostMapping("/sales")
    @ResponseStatus(HttpStatus.CREATED)
    public Sales createSale(@RequestBody @Valid Sales sales){
        return sellService.createSale(sales);
    }

    @GetMapping("/medicines-sold")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicineSold> findAllMedicinesSold(){
        return sellService.findAllMedicinesSold();
    }

    @GetMapping("/medicines-sold/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MedicineSold findMedicineSoldById(@PathVariable Long id){
        return sellService.findMedicineSoldById(id);
    }

    @GetMapping("/medicines-sold/sales/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicineSold> findMedicineSoldBySalesId(@PathVariable Long id){
        return sellService.findMedicineSoldBySalesId(id);
    }

    @PostMapping("/medicines-sold")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MedicineSold> createMedicineSold(@RequestBody @Valid List<MedicineSold> medicines){
        return sellService.storeMedicinesSold(medicines);
    }

    @PostMapping("/sales/make-sale")
    @ResponseStatus(HttpStatus.CREATED)
    public void makeSale(@RequestBody @Valid List<MedicinesToSellDTO> medicinesToSellDTOS) {
        sellService.makeSale(medicinesToSellDTOS);
    }
}
