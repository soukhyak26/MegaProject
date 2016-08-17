package com.affaince.subscription.common.type;

/**
 * Created by mandar on 17-08-2016.
 */
public enum ProductDemandTrend {
    NOCHANGE(0), UPWARD(1), DOWNWARD(2);
    private int trendCode;

    ProductDemandTrend(int trendCode) {
        this.trendCode = trendCode;
    }

    public static ProductDemandTrend valueOf(int trendCode) {
        switch (trendCode) {
            case 0:
                return NOCHANGE;
            case 1:
                return UPWARD;
            case 2:
                return DOWNWARD;
            default:
                return NOCHANGE;

        }
    }

    public int getTrendCode() {
        return trendCode;
    }
}
