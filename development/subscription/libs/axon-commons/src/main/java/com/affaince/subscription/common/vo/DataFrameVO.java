package com.affaince.subscription.common.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandar on 6/21/2017.
 */
public class DataFrameVO implements Serializable {
    private LocalDate date;
    private String token;
    private double value;

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
}
