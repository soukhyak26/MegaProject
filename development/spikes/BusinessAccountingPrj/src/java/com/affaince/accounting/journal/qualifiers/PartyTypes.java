package com.affaince.accounting.journal.qualifiers;

public enum PartyTypes {
    SUPPLIER_OF_GOODS(AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT),
    SERVICE_PROVIDER(AccountIdentifier.SERVICE_PROVIDER_ACCOUNT),
    SUBSCRIBER(AccountIdentifier.SUBSCRIBER_ACCOUNT),
    DISTRIBUTION_SUPPLIER(AccountIdentifier.DISTRIBUTION_SUPPLIER_ACCOUNT),
    MERCHANT(AccountIdentifier.BUSINESS_CAPITAL_ACCOUNT),
    TAX_AUTHORITY(AccountIdentifier.TAX_ACCOUNT),
    OWNER_OF_PREMISE_RENTED(AccountIdentifier.OWNER_OF_PREMISE_RENTED_ACCOUNT),

    SUBSCRIBER_REWARDS(AccountIdentifier.SUBSCRIBER_REWARDS_ACCOUNT),
    BUSINESS_CAPITAL(AccountIdentifier.BUSINESS_CAPITAL_ACCOUNT),
    BUSINESS_GOODS(AccountIdentifier.BUSINESS_PURCHASE_ACCOUNT),
    BUSINESS_BANK_ACCOUNT(AccountIdentifier.BUSINESS_BANK_ACCOUNT),
    EMPLOYEE(AccountIdentifier.EMPLOYEE_SALARY_ACCOUNT),
    FIXED_ASSETS(AccountIdentifier.FIXED_ASSETS_ACCOUNT),
    BUSINESS(null);

    private AccountIdentifier accountIdentifier;

    PartyTypes(AccountIdentifier accountIdentifier) {
        this.accountIdentifier = accountIdentifier;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
    }
}
