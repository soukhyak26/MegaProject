package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.NominalAccount;

public class SalaryLedgerAccount extends AbstractLedgerAccountStereoType implements NominalAccount {
    public SalaryLedgerAccount(String accountId) {
        super(accountId);
    }
}
