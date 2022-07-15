package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccount;
import com.affaince.accounting.accounts.types.NominalAccount;

public class SalaryLedgerAccount extends AbstractLedgerAccount implements NominalAccount {
    public SalaryLedgerAccount(String accountId) {
        super(accountId);
    }
}
