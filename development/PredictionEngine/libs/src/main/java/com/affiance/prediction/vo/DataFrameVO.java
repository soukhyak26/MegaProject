package com.affiance.prediction.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.LocalDate;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by mandar on 6/21/2017.
 */
public class DataFrameVO implements Serializable {
    public static final String ISO_LOCAL_DATE_PATTERN = "yyyy-MM-dd";
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ISO_LOCAL_DATE_PATTERN)
    private LocalDate date;
    private String token;
    private double value;

    public DataFrameVO(){}
    public DataFrameVO(LocalDate date, String token, double value) {
        this.date = date;
        this.token = token;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getToken() {
        return token;
    }

    public double getValue() {
        return value;
    }

    public void setDate(String date) {
        this.date=DateTimeFormat.forPattern(ISO_LOCAL_DATE_PATTERN).parseLocalDate(date);
    }
    @JsonIgnore
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
