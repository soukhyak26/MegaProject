package com.affaince.accounting.journal.qualifiers;

public enum AccountIdentifier {
    SUPPLIER_OF_GOODS_ACCOUNT(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT,false),
    SERVICE_PROVIDER_ACCOUNT(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT,false),
    SUBSCRIBER_ACCOUNT(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT,false),
    SUBSCRIBER_REWARDS_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT,false),
    DISTRIBUTION_SUPPLIER_ACCOUNT(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT,false),
    EMPLOYEE_SALARY_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT,false),
    OWNER_OF_PREMISE_RENTED_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT,false),
    TAX_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT,false),
    FIXED_ASSETS_ACCOUNT(AccountQualifiers.REAL_LEDGER_ACCOUNT,false),

    BUSINESS_CAPITAL_ACCOUNT(AccountQualifiers.REAL_LEDGER_ACCOUNT,false),
    BUSINESS_PURCHASE_ACCOUNT(AccountQualifiers.REAL_LEDGER_ACCOUNT,false),
    BUSINESS_PURCHASE_RETURN_ACCOUNT(AccountQualifiers.REAL_LEDGER_ACCOUNT,true),
    BUSINESS_SALES_RETURN_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT, false),
    BUSINESS_CASH_ACCOUNT(AccountQualifiers.REAL_LEDGER_ACCOUNT, false),
    BUSINESS_BANK_ACCOUNT(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT,false),
    BUSINESS_SERVICES_AVAILED_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT,false),
    BUSINESS_SALES_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT,true),
    BUSINESS_PROFIT_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT,true),
    BUSINESS_LOSS_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT,false),
    BUSINESS_DISCOUNT_ALLOWED_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT,false),
    BUSINESS_DISCOUNT_RECEIVED_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT,false),

    TO_BALANCE_CARRIED_DOWN(null,false),
    TO_BALANCE_BROUGHT_DOWN(null,false),
    BY_BALANCE_CARRIED_DOWN(null,false),
    BY_BALANCE_BROUGHT_DOWN(null,false),

    TRADING_ACCOUNT(null,false),
    PROFITANDLOSS_ACCOUNT(null,false),
    OPENING_STOCK_ACCOUNT(null,false),
    CLOSING_STOCK_ACCOUNT(null,false),

    GROSS_PROFIT(null, false),
    GROSS_LOSS (null, false ),

    NET_PROFIT(null, false),
    NET_LOSS(null, false);

    private AccountQualifiers accountQualifiers;
    private boolean isGain;
    AccountIdentifier(AccountQualifiers accountQualifiers,boolean isGain){
        this.accountQualifiers = accountQualifiers;
        this.isGain=isGain;
    }

    public AccountQualifiers getAccountQualifiers() {
        return accountQualifiers;
    }

    public boolean isGain() {
        return isGain;
    }
}
