package com.ironhack.OrderService.service;

import com.ironhack.OrderService.exceptions.ResourceNotFoundException;
import com.ironhack.OrderService.model.MedicineOrdered;
import com.ironhack.OrderService.model.Order;
import com.ironhack.OrderService.repository.MedicineOrderedRepository;
import com.ironhack.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineOrderedService {
    @Autowired
    private MedicineOrderedRepository medicineOrderedRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<MedicineOrdered> findAll() {
        return medicineOrderedRepository.findAllByOrderByOrderIdDesc();
    }

    public MedicineOrdered findById(Long id) {
        return medicineOrderedRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Medicine Ordered not found with that id")
        );
    }

    public List<MedicineOrdered> findByOrderId(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order not found with that id")
        );
        return medicineOrderedRepository.findAllByOrderIdEquals(id);
    }

    public List<MedicineOrdered> storeMedicines(List<MedicineOrdered> medicines) {
        return medicineOrderedRepository.saveAll(medicines);
    }
}
