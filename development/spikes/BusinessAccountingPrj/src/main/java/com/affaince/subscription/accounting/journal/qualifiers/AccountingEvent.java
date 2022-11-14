package com.affaince.subscription.accounting.journal.qualifiers;

public enum AccountingEvent {
    // capital investment - no exception
    CAPITAL_INVESTMENT,
    //purchase is on credit so should not have exception situation
    GOODS_PURCHASE_BY_BUSINESS,
    PURCHASE_RETURN_BY_BUSINESS,
    SUPPLIER_PAYMENT_TOWARDS_PURCHASE_RETURN,
    PAYMENT_MADE_TO_SUPPLIER,
    //delivery should enable debiting bank account by eq. amount. so should not have exceptional situation
    GOODS_DELIVERY_TO_SUBSCRIBER,
    //The invoice may have discount component by the service provider
    GOODS_RETURN_FROM_SUBSCRIBER,
    SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER,
    PAYMENT_RECEIVED_FROM_SUBSCRIBER,

    PAYMENT_MADE_TO_SERVICE_PROVIDER,
    TAX_PAYMENT,
    REWARDS_OFFERED_TO_SUBSCRIBER,
    REWARDS_REDEEMED_BY_SUBSCRIBER,
    PAYMENT_OF_RENT,
    STATIONARY_OR_FURNITURE_PURCHASE;


}
