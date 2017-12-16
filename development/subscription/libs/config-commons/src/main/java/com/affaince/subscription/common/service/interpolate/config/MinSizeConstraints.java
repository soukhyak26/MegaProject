package com.affaince.subscription.common.service.interpolate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mandar on 2/28/2017.
 */
@Configuration
@ConfigurationProperties(prefix= "interpolator.threshold_min")
public class MinSizeConstraints {
    private int sli;
    private int csi;

    public int getSli() {
        return sli;
    }

    public void setSli(int sli) {
        this.sli = sli;
    }

    public int getCsi() {
        return csi;
    }

    public void setCsi(int csi) {
        this.csi = csi;
    }
}
