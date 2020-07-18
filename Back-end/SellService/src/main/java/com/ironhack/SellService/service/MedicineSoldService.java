package com.ironhack.SellService.service;

import com.ironhack.SellService.exceptions.ResourceNotFoundException;
import com.ironhack.SellService.model.MedicineSold;
import com.ironhack.SellService.model.Sales;
import com.ironhack.SellService.repository.MedicineSoldRepository;
import com.ironhack.SellService.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineSoldService {
    @Autowired
    private MedicineSoldRepository medicineSoldRepository;
    @Autowired
    private SalesRepository salesRepository;

    public List<MedicineSold> findAll () {
        return medicineSoldRepository.findAllByOrderBySalesIdDesc();
    }

    public MedicineSold findById (Long id) {
        return medicineSoldRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Medicine Sold not found with that id")
        );
    }

    public List<MedicineSold> findBySalesId(Long id) {
        Sales sales = salesRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sales not found with that id")
        );
        return medicineSoldRepository.findAllBySalesIdEquals(id);
    }

    public List<MedicineSold> storeMedicinesSold(List<MedicineSold> medicines) {
        return medicineSoldRepository.saveAll(medicines);
    }

}
