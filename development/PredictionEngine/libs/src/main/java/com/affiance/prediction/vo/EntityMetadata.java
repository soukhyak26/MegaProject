package com.affiance.prediction.vo;

import java.util.Map;

/**
 * Created by mandar on 9/23/2017.
 */
public class EntityMetadata {
    private Map<String,Object> namedEntries;
    public EntityMetadata(){}

    public EntityMetadata(Map<String, Object> namedEntries) {
        this.namedEntries = namedEntries;
    }

    public Map<String, Object> getNamedEntries() {
        return namedEntries;
    }

    public void setNamedEntries(Map<String, Object> namedEntries) {
        this.namedEntries = namedEntries;
    }
}
