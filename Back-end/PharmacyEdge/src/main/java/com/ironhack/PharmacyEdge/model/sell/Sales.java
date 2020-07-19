package com.ironhack.PharmacyEdge.model.sell;

import com.ironhack.PharmacyEdge.classes.Money;

import javax.validation.constraints.NotNull;
import java.util.Calendar;

public class Sales {
    private Long id;
    @NotNull(message = "User Id can not be null")
    private Integer userId;
    private Integer patientId;
    private Calendar date;
    @NotNull(message = "Total price required")
    private Money totalPrice;

    public Sales() {
        Calendar cal = Calendar.getInstance();
        this.date = cal;
    }

    public Sales(Integer userId, Integer patientId, Money totalPrice) {
        this.userId = userId;
        setPatientId(patientId);
        Calendar cal = Calendar.getInstance();
        this.date = cal;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = (patientId != null && patientId >= 2) ? patientId : 1;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Money totalPrice) {
        this.totalPrice = totalPrice;
    }
}
