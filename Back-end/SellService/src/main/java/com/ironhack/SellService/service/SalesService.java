package com.ironhack.SellService.service;

import com.ironhack.SellService.exceptions.ResourceNotFoundException;
import com.ironhack.SellService.model.Sales;
import com.ironhack.SellService.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {
    @Autowired
    private SalesRepository salesRepository;

    public List<Sales> findAll() {
        return salesRepository.findAllByOrderByDateDesc();
    }

    public Sales findById(Long id) {
        return salesRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sales not found with that id")
        );
    }

    public Sales findLastSale() {
        return salesRepository.findLastSale();
    }

    public Sales store(Sales sales) {
        return salesRepository.save(sales);
    }
}
