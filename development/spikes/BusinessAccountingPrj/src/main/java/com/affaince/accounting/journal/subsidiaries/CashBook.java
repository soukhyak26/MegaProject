package com.affaince.accounting.journal.subsidiaries;

import java.util.ArrayList;
import java.util.List;

public class CashBook {
    private final String merchantId;
    private final List<DebitCashBookEntry> debitCashBookEntries;
    private final List<CreditCashBookEntry> creditCashBookEntries;

    public CashBook(String merchantId) {
        this.merchantId = merchantId;
        debitCashBookEntries= new ArrayList<>();
        creditCashBookEntries= new ArrayList<>();
    }

    public void addDebitCashBookEntry(DebitCashBookEntry debitCashBookEntry){
        this.debitCashBookEntries.add(debitCashBookEntry);
    }

    public void addCreditCashBookEntry(CreditCashBookEntry creditCashBookEntry){
        this.creditCashBookEntries.add(creditCashBookEntry);
    }

    public String getMerchantId() {
        return merchantId;
    }

    public List<DebitCashBookEntry> getDebitCashBookEntries() {
        return debitCashBookEntries;
    }

    public List<CreditCashBookEntry> getCreditCashBookEntries() {
        return creditCashBookEntries;
    }
}
