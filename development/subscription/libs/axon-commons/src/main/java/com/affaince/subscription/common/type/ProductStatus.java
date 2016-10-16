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
    //manual pseudoActuals for next day are assigned
    PRODUCT_STEPFORECAST_CREATED(4),

    //opening price has been assigned.
    PRODUCT_PRICE_ASSIGNED(5),

    PRODUCT_ACTIVATED(6),

    //If product is deactivated due to any exception or through forceful deactivation( taking our product from subscription business)
    PRODUCT_DEACTIVATED(7);

    private int statusCode;

    ProductStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
