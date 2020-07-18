package com.ironhack.OrderService.service;

import com.ironhack.OrderService.exceptions.ResourceNotFoundException;
import com.ironhack.OrderService.model.MedicinesOrdered;
import com.ironhack.OrderService.model.Order;
import com.ironhack.OrderService.repository.MedicinesOrderedRepository;
import com.ironhack.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicinesOrderedService {
    @Autowired
    private MedicinesOrderedRepository medicinesOrderedRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<MedicinesOrdered> findAll() {
        return medicinesOrderedRepository.findAllByOrderByOrderIdDesc();
    }

    public MedicinesOrdered findById(Long id) {
        return medicinesOrderedRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Medicine Ordered not found with that id")
        );
    }

    public List<MedicinesOrdered> findByOrderId(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order not found with that id")
        );
        return medicinesOrderedRepository.findAllByOrderIdEquals(id);
    }

    public List<MedicinesOrdered> storeMedicines(List<MedicinesOrdered> medicines) {
        return medicinesOrderedRepository.saveAll(medicines);
    }

}
