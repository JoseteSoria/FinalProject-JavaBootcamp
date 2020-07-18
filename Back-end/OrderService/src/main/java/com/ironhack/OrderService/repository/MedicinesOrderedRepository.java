package com.ironhack.OrderService.repository;

import com.ironhack.OrderService.model.MedicinesOrdered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicinesOrderedRepository extends JpaRepository<MedicinesOrdered, Long> {
    List<MedicinesOrdered> findAllByOrderByOrderIdDesc();

    List<MedicinesOrdered> findAllByOrderIdEquals(Long id);
}
