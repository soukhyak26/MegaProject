package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccount;
import com.affaince.accounting.accounts.types.NominalAccount;

public class DiscountLedgerAccount extends AbstractLedgerAccount implements NominalAccount {
    public DiscountLedgerAccount(String accountId) {
        super(accountId);
    }
}
