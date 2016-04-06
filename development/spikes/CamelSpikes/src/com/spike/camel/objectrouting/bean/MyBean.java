package com.spike.camel.objectrouting.bean;

import com.spike.camel.objectrouting.determine.ProcessorType;

/**
 * Created by mandark on 03-04-2016.
 */
public class MyBean {
    private String beanId;
    private double beanValue;
    private ProcessorType processorType;

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

    public ProcessorType getProcessorType() {
        return this.processorType;
    }

    public void setProcessorType(ProcessorType processorType) {
        this.processorType = processorType;
    }
}
