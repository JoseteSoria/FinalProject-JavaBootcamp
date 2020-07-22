package com.ironhack.ReportService.repository;


import com.ironhack.ReportService.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

    @Query(value = "SELECT u.name, COUNT(*), SUM(total_price) FROM sales s JOIN user u ON s.user_id = u.id " +
            "WHERE date >:date GROUP BY s.user_id", nativeQuery = true)
    List<Object[]> findSalesByUserInMonthsPeriod(@Param("date") Calendar date);

    @Query(value = "SELECT p.name, COUNT(*), SUM(total_price) FROM sales s JOIN patient p ON s.patient_id = p.id " +
            "WHERE date >:date GROUP BY s.patient_id", nativeQuery = true)
    List<Object[]> findPurchasesByPatientInMonthsPeriod(@Param("date") Calendar date);

}
