package com.affaince.subscription.common.type;

/**
 * Created by anayonkar on 13/2/16.
 */
public enum ProductStatus {

    //after product registration
    PRODUCT_REGISTERED(1),

    //dependent upon registration
    PRODUCT_CONFIGURED(2),

    //dependent upon registration
    PRODUCT_FORECASTED(3),

    PRODUCT_STEPFORECAST_CREATED(4),

    PRODUCT_ACTIVATED(5),

    PRODUCT_DEACTIVATED(6);

    private int statusCode;

    ProductStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
