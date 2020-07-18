package com.ironhack.SellService.repository;

import com.ironhack.SellService.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findAllByOrderByDateDesc();

    @Query(value = "SELECT * FROM sales ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Sales findLastSale();
}
