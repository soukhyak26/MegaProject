package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccount;
import com.affaince.accounting.accounts.types.NominalAccount;

public class SalesAccount extends AbstractLedgerAccount implements NominalAccount {
    public SalesAccount(String accountId) {
        super(accountId);
    }
}
