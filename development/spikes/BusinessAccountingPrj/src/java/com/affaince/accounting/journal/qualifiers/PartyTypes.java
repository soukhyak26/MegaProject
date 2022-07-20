package com.affaince.accounting.journal.qualifiers;

public enum PartyTypes {
    SUPPLIER_OF_GOODS(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT),
    SERVICE_PROVIDER(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT),
    SUBSCRIBER(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT),
    SUBSCRIBER_REWARDS(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT),
    MERCHANT(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT),
    BUSINESS_CAPITAL(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT),
    BUSINESS_GOODS(AccountQualifiers.REAL_LEDGER_ACCOUNT),
    BUSINESS_BANK_ACCOUNT(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT),
    DISTRIBUTION_SUPPLIER(AccountQualifiers.PERSONAL_LEDGER_ACCOUNT),
    EMPLOYEE_SALARY(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT),
    OWNER_OF_PREMISE_RENTED(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT),
    TAX_AUTHORITY(AccountQualifiers.NOMINAL_LEDGER_ACCOUNT),
    FIXED_ASSETS(AccountQualifiers.REAL_LEDGER_ACCOUNT),
    BUSINESS(null);

    private AccountQualifiers accountQualifiers;
    PartyTypes(AccountQualifiers accountQualifiers){
        this.accountQualifiers = accountQualifiers;
    }

    public AccountQualifiers getAccountQualifiers() {
        return accountQualifiers;
    }
}
