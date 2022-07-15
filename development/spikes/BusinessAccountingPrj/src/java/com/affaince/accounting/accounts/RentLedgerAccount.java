package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccount;
import com.affaince.accounting.accounts.types.NominalAccount;

public class RentLedgerAccount extends AbstractLedgerAccount implements NominalAccount {
    public RentLedgerAccount(String accountId) {
        super(accountId);
    }
}
