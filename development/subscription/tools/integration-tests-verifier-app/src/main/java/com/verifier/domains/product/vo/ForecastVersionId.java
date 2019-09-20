package com.verifier.domains.product.vo;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandar on 9/24/2017.
 */
public class ForecastVersionId extends ProductVersionId implements Serializable{
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate forecastDate;


    public ForecastVersionId(){}
    public ForecastVersionId(String productId, LocalDate startDate, LocalDate forecastDate) {
        super(productId,startDate);
        this.forecastDate = forecastDate;
    }
    @JsonProperty("forecastDate")
    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ForecastVersionId that = (ForecastVersionId) o;

        return forecastDate.equals(that.forecastDate);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + forecastDate.hashCode();
        return result;
    }
}
