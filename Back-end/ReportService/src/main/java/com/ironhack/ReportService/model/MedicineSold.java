package com.ironhack.ReportService.model;


import com.ironhack.ReportService.classes.Money;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class MedicineSold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Medicine Id can not be null")
    private Long medicineId;
    @NotNull(message = "Medicine name can not be null")
    private String medicineName;
    @NotNull(message = "Money can not be null")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency")),
    })
    private Money price;
    @NotNull(message = "Sales Id can not be null")
    private Long salesId;

    public MedicineSold(){}

}
