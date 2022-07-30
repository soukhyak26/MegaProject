package com.affaince.accounting.ledger.accounts.types;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractLedgerAccountStereoType implements LedgerAccount{
    private String merchantId;
    private String accountId;
    private AccountIdentifier accountIdentifier;
    Set<DebitLedgerEntry> debits;
    Set<CreditLedgerEntry> credits;

    public AbstractLedgerAccountStereoType(String merchantId,String accountId,AccountIdentifier accountIdentifier) {
        this.merchantId=merchantId;
        this.accountId= accountId;
        this.accountIdentifier = accountIdentifier;
       // AccountEntryComparator comparator = new AccountEntryComparator();
        this.debits = new TreeSet<>(Comparator.comparing(LedgerAccountEntry::getDate));
        this.credits = new TreeSet<>(Comparator.comparing(LedgerAccountEntry::getDate));
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getAccountId() {
        return accountId;
    }


    public Set<DebitLedgerEntry> getDebits() {
        return debits;
    }

    public Set<CreditLedgerEntry> getCredits() {
        return credits;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
    }

    public void debit(DebitLedgerEntry debitEntry){
        if( null != debitEntry) {
            this.debits.add(debitEntry);
        }
    }
    public void credit(CreditLedgerEntry creditEntry){
        if( null != creditEntry){
            this.credits.add(creditEntry);
        }
    }

    @Override
    public String toString() {
        return "AbstractLedgerAccountStereoType{" +
                "merchantId='" + merchantId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", accountIdentifier=" + accountIdentifier +
                ", debits=" + debits +
                ", credits=" + credits +
                '}';
    }
}
