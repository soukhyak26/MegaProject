package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.NominalAccount;

public class RentLedgerAccount extends AbstractLedgerAccountStereoType implements NominalAccount {
    public RentLedgerAccount(String accountId) {
        super(accountId);
    }
}
