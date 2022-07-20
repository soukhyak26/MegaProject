package com.affaince.accounting.journal.qualifiers;

public enum TransactionEvents {
    // capital investment - no exception
    CAPITAL_INVESTMENT(PartyTypes.MERCHANT, PartyTypes.BUSINESS_BANK_ACCOUNT),
    //purchase is on credit so should not have exception situation
    GOODS_PURCHASE_BY_BUSINESS(PartyTypes.SUPPLIER_OF_GOODS, PartyTypes.BUSINESS_GOODS),
    PAYMENT_MADE_TO_SUPPLIER(PartyTypes.BUSINESS_BANK_ACCOUNT,PartyTypes.SUPPLIER_OF_GOODS),
    //delivery should enable debiting bank account by eq. amount. so should not have exceptional situation
    GOODS_DELIVERY_TO_SUBSCRIBER(PartyTypes.SUPPLIER_OF_GOODS, PartyTypes.BUSINESS_GOODS),
    //The invoice may have discount component by the service provider
    SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER(PartyTypes.SERVICE_PROVIDER,PartyTypes.BUSINESS_BANK_ACCOUNT),
    PAYMENT_RECEIVED_FROM_SUBSCRIBER(PartyTypes.SUBSCRIBER,PartyTypes.BUSINESS_BANK_ACCOUNT),

    PAYMENT_MADE_TO_SERVICE_PROVIDER(PartyTypes.BUSINESS_BANK_ACCOUNT,PartyTypes.SERVICE_PROVIDER),
    TAX_PAYMENT(PartyTypes.BUSINESS_BANK_ACCOUNT,PartyTypes.TAX_AUTHORITY),
    REWARDS_OFFERED_TO_SUBSCRIBER(PartyTypes.BUSINESS_BANK_ACCOUNT,PartyTypes.SUBSCRIBER_REWARDS),
    REWARDS_REDEEMED_BY_SUBSCRIBER(PartyTypes.SUBSCRIBER_REWARDS,PartyTypes.BUSINESS_BANK_ACCOUNT),
    PAYMENT_OF_RENT(PartyTypes.BUSINESS_BANK_ACCOUNT,PartyTypes.OWNER_OF_PREMISE_RENTED),
    STATIONARY_OR_FURNITURE_PURCHASE(PartyTypes.BUSINESS_BANK_ACCOUNT,PartyTypes.FIXED_ASSETS);

    private PartyTypes giver;
    private PartyTypes receiver;

    TransactionEvents(PartyTypes giver, PartyTypes receiver) {
        this.giver = giver;
        this.receiver = receiver;
    }

    public PartyTypes getGiver() {
        return giver;
    }

    public PartyTypes getReceiver() {
        return receiver;
    }
}
