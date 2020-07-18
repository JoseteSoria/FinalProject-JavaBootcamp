package com.ironhack.MedicineService.repository;

import com.ironhack.MedicineService.model.WarehouseMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseMedicineRepository extends JpaRepository<WarehouseMedicine, Long> {
    @Query(value = "SELECT name, FROM(name) FROM warehouse_medicine GROUP BY name HAVING name = :name", nativeQuery = true)
    Optional<Object[]> findMedicinesByName(@Param("name") String name);
}
