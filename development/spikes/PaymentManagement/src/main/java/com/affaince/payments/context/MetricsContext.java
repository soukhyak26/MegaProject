package com.affaince.payments.context;

import java.util.HashMap;
import java.util.Map;

public class MetricsContext {

    private Map<String,Object> advancedPayMetrics;
    private Map<String,Object> residualPayMetrics;

    public MetricsContext() {
        advancedPayMetrics = new HashMap<>();
        residualPayMetrics =new HashMap<>();
    }

    public Object findValue(String metricName){
        Object value=null;
        value = advancedPayMetrics.entrySet().stream().filter(mtrc-> mtrc.getKey().equalsIgnoreCase(metricName)).findFirst().map(Map.Entry::getValue).orElse(null);
        if(null == value){
            value = residualPayMetrics.entrySet().stream().filter(mtrc-> mtrc.getKey().equalsIgnoreCase(metricName)).findFirst().map(Map.Entry::getValue).orElse(null);
        }
        return value;
    }

    public void addToAdvancePayMetrics(String key, Object value){
        advancedPayMetrics.put(key,value);
    }

    public void addToResidualPayMetrics(String key, Object value){
        residualPayMetrics.put(key,value);
    }

}
