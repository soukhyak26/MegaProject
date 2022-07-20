package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.NominalAccount;

public class ProfitAccount extends AbstractLedgerAccountStereoType implements NominalAccount {
    public ProfitAccount(String accountId) {
        super(accountId);
    }
}
