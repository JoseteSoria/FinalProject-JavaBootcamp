package com.ironhack.PharmacyEdge.model.order;

import java.util.Calendar;

public class Order {
    private Long id;
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
