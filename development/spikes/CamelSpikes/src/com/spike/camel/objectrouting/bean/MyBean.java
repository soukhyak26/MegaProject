package com.spike.camel.objectrouting.bean;

import com.spike.camel.objectrouting.determine.ProcessorType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandark on 03-04-2016.
 */
public class MyBean {
    private String beanId;
    private double beanValue;
    private ProcessorType processorType;
    private List<String> processorTokens;


    public MyBean(String beanId, double beanValue) {
        this.beanId = beanId;
        this.beanValue = beanValue;
        this.processorTokens=new ArrayList<>();
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
    public void addToProcessorTokens(String token){
        processorTokens.add(token);
    }

    public List<String> getProcessorTokens() {
        return this.processorTokens;
    }
}
