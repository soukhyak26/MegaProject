package com.affaince.subscription.common.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandar on 9/24/2017.
 */
public class ForecastVersionId extends ProductVersionId implements Serializable{
    private LocalDate forecastDate;

    public ForecastVersionId(){}
    public ForecastVersionId(String productId, LocalDate fromDate, LocalDate forecastDate) {
        super(productId,fromDate);
        this.forecastDate = forecastDate;
    }

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
