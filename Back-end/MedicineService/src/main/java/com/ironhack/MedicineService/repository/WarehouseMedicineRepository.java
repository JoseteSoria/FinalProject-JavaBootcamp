package com.ironhack.MedicineService.repository;

import com.ironhack.MedicineService.model.WarehouseMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseMedicineRepository extends JpaRepository<WarehouseMedicine, Long> {
    @Query(value = "SELECT * FROM warehouse_medicine WHERE id IN (SELECT id FROM warehouse_medicine WHERE name = :name )", nativeQuery = true)
    Optional<List<WarehouseMedicine>> findAllMedicinesByName(@Param("name") String name);

    @Query(value = "SELECT name, COUNT(name) FROM warehouse_medicine GROUP BY name HAVING name = :name", nativeQuery = true)
    Optional<Object[]> findMedicineQuantityByName(@Param("name") String name);

    @Query(value = "UPDATE warehouse_medicine as wh, (SELECT id FROM warehouse_medicine WHERE name = :name ) AS wh2 SET wh.price = :newPrice WHERE wh.id=wh2.id", nativeQuery = true)
    void updatePriceByName(@Param("name") String name, @Param("newPrice") String newPrice);

    @Query(value = "SELECT * FROM warehouse_medicine WHERE expiration_date < :date ", nativeQuery = true)
    Optional<List<WarehouseMedicine>> findMedicinesCloseToExpirationDate(@Param("date")Calendar date);
}
