package com.affaince.accounting.ledger.accounts.types;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

import java.util.Set;
import java.util.Stack;

public interface LedgerAccount extends Cloneable{
     public String getMerchantId();
     public String getAccountId();
     public AccountIdentifier getAccountIdentifier();
     public void debit(DebitLedgerEntry debitEntry);
     public void credit(CreditLedgerEntry creditEntry);
     public Set<DebitLedgerEntry> getDebits();
     public Set<CreditLedgerEntry> getCredits();
     public void addPreviousVersion(LedgerAccount ledgerAccount);
     public Stack<LedgerAccount> getPreviousVersions();
     public Object clone();
     public void flushLedgerAccountEntries();


}
