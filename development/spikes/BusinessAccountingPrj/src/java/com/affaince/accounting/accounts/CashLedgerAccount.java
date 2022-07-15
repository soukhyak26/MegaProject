package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccount;
import com.affaince.accounting.accounts.types.RealAccount;

public class CashLedgerAccount extends AbstractLedgerAccount implements RealAccount {
    public CashLedgerAccount(String accountId) {
        super(accountId);
    }
}
