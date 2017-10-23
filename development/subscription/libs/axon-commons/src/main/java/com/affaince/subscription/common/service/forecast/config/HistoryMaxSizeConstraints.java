package com.affaince.subscription.common.service.forecast.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "forecaster.threshold_max")
public class HistoryMaxSizeConstraints {
    private int slf;
    private int sma;
    private int sema;
    private int tema;
    private int arima;

    public int getSlf() {
        return slf;
    }

    public void setSlf(int slf) {
        this.slf = slf;
    }

    public int getSma() {
        return sma;
    }

    public void setSma(int sma) {
        this.sma = sma;
    }

    public int getSema() {
        return sema;
    }

    public void setSema(int sema) {
        this.sema = sema;
    }

    public int getTema() {
        return tema;
    }

    public void setTema(int tema) {
        this.tema = tema;
    }

    public int getArima() {
        return arima;
    }

    public void setArima(int arima) {
        this.arima = arima;
    }
}
