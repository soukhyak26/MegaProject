package com.affiance.prediction.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.io.Serializable;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataFrameVO that = (DataFrameVO) o;

        if (Double.compare(that.value, value) != 0) return false;
        if (!date.equals(that.date)) return false;
        return token.equals(that.token);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = date.hashCode();
        result = 31 * result + token.hashCode();
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
