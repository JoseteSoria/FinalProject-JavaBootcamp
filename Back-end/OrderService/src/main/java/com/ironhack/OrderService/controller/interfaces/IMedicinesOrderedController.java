package com.ironhack.OrderService.controller.interfaces;

import com.ironhack.OrderService.model.MedicinesOrdered;

import java.util.List;

public interface IMedicinesOrderedController {
    public List<MedicinesOrdered> findAll();

    public MedicinesOrdered findById(Long id);

    public List<MedicinesOrdered> findByOrderId(Long id);

    public List<MedicinesOrdered> create(List<MedicinesOrdered> medicines);
}
