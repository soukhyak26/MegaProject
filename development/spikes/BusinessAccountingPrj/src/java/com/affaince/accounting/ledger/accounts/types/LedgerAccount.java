package com.affaince.accounting.ledger.accounts.types;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public interface LedgerAccount {
     public String getMerchantId();
     public String getAccountId();
     public AccountIdentifier getAccountIdentifier();
     public void debit(DebitLedgerEntry debitEntry);
     public void credit(CreditLedgerEntry creditEntry);

}
