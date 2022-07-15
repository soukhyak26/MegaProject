package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccount;
import com.affaince.accounting.accounts.types.NominalAccount;

public class ProfitAccount extends AbstractLedgerAccount implements NominalAccount {
    public ProfitAccount(String accountId) {
        super(accountId);
    }
}
