package com.ironhack.MedicineService.repository;

import com.ironhack.MedicineService.model.WarehouseMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseMedicineRepository extends JpaRepository<WarehouseMedicine, Long> {
    Optional<WarehouseMedicine> findByName(String name);
}
