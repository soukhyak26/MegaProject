package com.affaince.subscription.product.vo;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by mandar on 08-07-2016.
 */

@Component
@ConfigurationProperties(prefix="forecaster.chain")
public class ForecastersList {
    private Map<String, String> elements=new HashedMap();

    public Map<String, String> getElements() {
        return elements;
    }

    public void setElements(Map<String, String> elements) {
        this.elements = elements;
    }
}
