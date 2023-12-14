package com.calculate.price.forecast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum AggregationType {
    DAILY_NEW,
    INCREMENTAL;

    private static final Map<String, AggregationType> namesMap = new HashMap(2);

    private AggregationType() {
    }

    public static AggregationType forValue(String value) {
        AggregationType type = (AggregationType)namesMap.get(value);
        if (type == null) {
            throw new IllegalArgumentException(value + " has no corresponding value$$$$");
        } else {
            return type;
        }
    }

    public String toValue() {
        Iterator var1 = namesMap.entrySet().iterator();

        Map.Entry entry;
        do {
            if (!var1.hasNext()) {
                return null;
            }

            entry = (Map.Entry)var1.next();
        } while(entry.getValue() != this);

        return (String)entry.getKey();
    }

    static {
        namesMap.put("DAILY_NEW", DAILY_NEW);
        namesMap.put("INCREMENTAL", INCREMENTAL);
    }
}

