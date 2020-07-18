package com.ironhack.MedicineService.repository;

import com.ironhack.MedicineService.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByName(String name);

    @Query(value = "SELECT * FROM medicine WHERE id =:id", nativeQuery = true)
    Optional<Medicine> findMedicineById(@Param("id") Long id);
}
