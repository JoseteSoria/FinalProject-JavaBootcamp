package com.ironhack.ReportService.model;

import com.ironhack.ReportService.classes.Money;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "User Id can not be null")
    private Integer userId;
    private Integer patientId;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "total_price")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency")),
    })
    private Money totalPrice;

    public Sales() {
        Calendar cal = Calendar.getInstance();
        this.date = cal;
    }

}
