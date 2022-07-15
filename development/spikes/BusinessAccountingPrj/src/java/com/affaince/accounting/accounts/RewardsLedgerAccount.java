package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccount;
import com.affaince.accounting.accounts.types.NominalAccount;

public class RewardsLedgerAccount extends AbstractLedgerAccount implements NominalAccount {
    public RewardsLedgerAccount(String accountId) {
        super(accountId);
    }
}
