package com.affaince.accounting.accounts.types;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.journal.qualifiers.AccountQualifiers;

import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractLedgerAccountStereoType {
    private String merchantId;
    private String accountId;
    private AccountIdentifier accountIdentifier;
    Set<DebitEntry> debits;
    Set<CreditEntry> credits;

    public AbstractLedgerAccountStereoType(String merchantId,String accountId,AccountIdentifier accountIdentifier) {
        this.merchantId=merchantId;
        this.accountId= accountId;
        this.accountIdentifier = accountIdentifier;
        AccountEntryComparator comparator = new AccountEntryComparator();
        this.debits = new TreeSet<>(comparator);
        this.credits = new TreeSet<>(comparator);
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getAccountId() {
        return accountId;
    }


    public Set<DebitEntry> getDebits() {
        return debits;
    }

    public Set<CreditEntry> getCredits() {
        return credits;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
    }

    public void debit(DebitEntry debitEntry){
        if( null != debitEntry) {
            this.debits.add(debitEntry);
        }
    }
    public void credit(CreditEntry creditEntry){
        if( null != creditEntry){
            this.credits.add(creditEntry);
        }
    }

}
