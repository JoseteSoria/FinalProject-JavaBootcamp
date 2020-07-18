package com.ironhack.SellService.repository;

import com.ironhack.SellService.model.MedicineSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineSoldRepository extends JpaRepository<MedicineSold, Long> {
    List<MedicineSold> findAllByOrderBySalesIdDesc();

    List<MedicineSold> findAllBySalesIdEquals(Long id);
}
