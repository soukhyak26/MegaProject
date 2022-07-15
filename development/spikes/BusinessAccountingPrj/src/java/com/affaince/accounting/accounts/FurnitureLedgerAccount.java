package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccount;
import com.affaince.accounting.accounts.types.RealAccount;

public class FurnitureLedgerAccount extends AbstractLedgerAccount implements RealAccount {
    public FurnitureLedgerAccount(String accountId) {
        super(accountId);
    }
}
