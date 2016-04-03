package com.spike.camel.objectrouting.bean;

/**
 * Created by mandark on 03-04-2016.
 */
public class MyBean {
    private String beanId;
    private double beanValue;

    public MyBean(String beanId, double beanValue) {
        this.beanId = beanId;
        this.beanValue = beanValue;
    }

    @Override
    public String toString() {
        return "$$$$$$$$$$$MyBean{" +
                "beanId='" + beanId + '\'' +
                ", beanValue=" + beanValue +
                '}';
    }

    public String getBeanId() {
        return this.beanId;
    }

    public double getBeanValue() {
        return this.beanValue;
    }
}
