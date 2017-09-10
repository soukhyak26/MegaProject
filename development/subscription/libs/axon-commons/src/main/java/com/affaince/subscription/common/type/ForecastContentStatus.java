package com.affaince.subscription.common.type;

/*
@JsonSerialize(using = ForecastContentStatusSerializer.class)
@JsonDeserialize(using = ForecastContentStatusDeserializer.class)
*/
public enum ForecastContentStatus {
    ACTIVE(1), EXPIRED(2);

    private int forecastStatusValue;

    ForecastContentStatus(int forecastStatusValue) {
        this.forecastStatusValue = forecastStatusValue;
    }

    public static ForecastContentStatus valueOf(int value) {
        for (ForecastContentStatus r : ForecastContentStatus.values()) {
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
