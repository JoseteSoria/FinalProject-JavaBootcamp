package com.ironhack.OrderService.repository;

import com.ironhack.OrderService.model.MedicineOrdered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineOrderedRepository extends JpaRepository<MedicineOrdered, Long> {
    List<MedicineOrdered> findAllByOrderByOrderIdDesc();

    List<MedicineOrdered> findAllByOrderIdEquals(Long id);
}
