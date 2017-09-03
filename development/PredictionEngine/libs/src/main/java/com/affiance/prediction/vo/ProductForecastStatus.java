package com.affiance.prediction.vo;

/*
@JsonSerialize(using = ProductForecastStatusSerializer.class)
@JsonDeserialize(using = ProductForecastStatusDeserializer.class)
*/
public enum ProductForecastStatus {
    ACTIVE(1), EXPIRED(2);

    private int forecastStatusValue;

    ProductForecastStatus(int forecastStatusValue) {
        this.forecastStatusValue = forecastStatusValue;
    }

    public static ProductForecastStatus valueOf(int value) {
        for (ProductForecastStatus r : ProductForecastStatus.values()) {
            if (r.getForecastStatusValue() == value) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getForecastStatusValue() {
        return forecastStatusValue;
    }
}
