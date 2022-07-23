package com.affaince.accounting.accounts.types;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public interface LedgerAccount {
     public String getAccountId();
     public AccountIdentifier getAccountIdentifier();

}
