package com.ironhack.PharmacyEdge.model.order;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

public class Order {
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    public Order() {
        Calendar cal = Calendar.getInstance();
        date = cal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
