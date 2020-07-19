package com.ironhack.MedicineService.repository;

import com.ironhack.MedicineService.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
//    @Query(value = "SELECT * FROM medicine WHERE name =:name", nativeQuery = true)
//    Optional<Medicine> findNameOfMedicine(@Param("name") String name);
//
//    @Query(value = "SELECT m.* FROM medicine m WHERE m.id =:medicineId", nativeQuery = true)
//    Optional<Object> findYourMedicineById(@Param("medicineId") Long medicineId);
    Optional<Medicine> findByName(String name);
}
