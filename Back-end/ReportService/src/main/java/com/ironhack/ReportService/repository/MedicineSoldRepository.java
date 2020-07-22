package com.ironhack.ReportService.repository;

import com.ironhack.ReportService.model.MedicineSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface MedicineSoldRepository extends JpaRepository<MedicineSold, Long> {

    @Query(value = "SELECT m.medicine_name, COUNT(*) FROM medicine_sold m JOIN sales s ON m.sales_id = s.id" +
            " WHERE s.date >:date GROUP BY m.medicine_name ORDER BY COUNT(*) DESC LIMIT :ranking", nativeQuery = true)
    public List<Object[]> findMoreMedicineSoldPeriodRanking(@Param("date") Calendar date, Integer ranking);

    @Query(value = "SELECT m.medicine_name,  SUM(m.price) FROM medicine_sold m JOIN sales s ON m.sales_id = s.id" +
            " WHERE s.date >:date GROUP BY m.medicine_name ORDER BY SUM(m.price) DESC LIMIT :ranking", nativeQuery = true)
    public List<Object[]> findMoreMedicineRevenuePeriodRanking(@Param("date") Calendar date, Integer ranking);

}
