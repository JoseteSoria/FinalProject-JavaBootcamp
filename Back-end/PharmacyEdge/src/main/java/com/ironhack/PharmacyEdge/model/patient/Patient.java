package com.ironhack.PharmacyEdge.model.patient;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Calendar;

public class Patient {
    private Integer id;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @Temporal(TemporalType.DATE)
    private Calendar birthday;
    @NotNull
    @Pattern(regexp = "[0-9]{9}")
    private String phoneNumber;

    public Patient() {
    }

    public Patient(String name, Calendar birthday, String phoneNumber) {
        this.name = name;
        this.birthday = birthday;
        setPhoneNumber(phoneNumber);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = (phoneNumber != null) ? phoneNumber : "000000000";
    }
}
