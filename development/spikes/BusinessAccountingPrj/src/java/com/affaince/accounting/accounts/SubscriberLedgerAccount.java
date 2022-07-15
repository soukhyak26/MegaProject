package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccount;
import com.affaince.accounting.accounts.types.NominalAccount;

public class SubscriberLedgerAccount extends AbstractLedgerAccount implements NominalAccount {
    public SubscriberLedgerAccount(String accountId) {
        super(accountId);
    }
}
