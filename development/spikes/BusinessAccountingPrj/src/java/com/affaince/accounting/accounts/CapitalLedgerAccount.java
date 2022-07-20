package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.PersonalAccount;

public class CapitalLedgerAccount extends AbstractLedgerAccountStereoType implements PersonalAccount {

    public CapitalLedgerAccount(String accountId) {
        super(accountId);
    }
}
