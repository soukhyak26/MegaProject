package com.affaince.accounting.journal.qualifiers;

public enum AccountIdentifier {
    SUPPLIER_OF_GOODS_ACCOUNT(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT),
    SERVICE_PROVIDER_ACCOUNT(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT),
    SUBSCRIBER_ACCOUNT(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT),
    SUBSCRIBER_REWARDS_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT),
    MERCHANT_ACCOUNT(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT),
    DISTRIBUTION_SUPPLIER_ACCOUNT(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT),
    EMPLOYEE_SALARY_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT),
    OWNER_OF_PREMISE_RENTED_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT),
    TAX_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT),
    FIXED_ASSETS_ACCOUNT(AccountQualifiers.REAL_LEDGER_ACCOUNT),

    BUSINESS_CAPITAL_ACCOUNT(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT),
    BUSINESS_PURCHASE_ACCOUNT(AccountQualifiers.REAL_LEDGER_ACCOUNT),
    BUSINESS_SALES_RETURN_ACCOUNT(AccountQualifiers.REAL_LEDGER_ACCOUNT),
    BUSINESS_CASH_ACCOUNT(AccountQualifiers.REAL_LEDGER_ACCOUNT),
    BUSINESS_BANK_ACCOUNT(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT),
    BUSINESS_SERVICES_AVAILED_ACCOUNT(AccountQualifiers.REAL_LEDGER_ACCOUNT),
    BUSINESS_SALES_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT),
    BUSINESS_PROFIT_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT),
    BUSINESS_LOSS_ACCOUNT(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT),
    ALL_PROFIT_AND_LOSS_ACCOUNTS(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT);

    private AccountQualifiers accountQualifiers;
    AccountIdentifier(AccountQualifiers accountQualifiers){
        this.accountQualifiers = accountQualifiers;
    }

    public AccountQualifiers getAccountQualifiers() {
        return accountQualifiers;
    }

}
