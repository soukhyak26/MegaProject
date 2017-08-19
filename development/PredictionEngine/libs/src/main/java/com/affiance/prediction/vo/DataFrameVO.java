package com.affiance.prediction.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.affiance.prediction.deserializer.DataFrameVODeserializer;
import org.joda.time.LocalDate;
import com.affiance.prediction.serializer.DataFrameVOSerializer;

import java.io.Serializable;

/**
 * Created by mandar on 6/21/2017.
 */
@JsonSerialize(using= DataFrameVOSerializer.class)
@JsonDeserialize(using = DataFrameVODeserializer.class)
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
