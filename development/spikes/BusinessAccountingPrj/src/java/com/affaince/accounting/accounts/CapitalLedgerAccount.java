package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccount;
import com.affaince.accounting.accounts.types.PersonalAccount;

public class CapitalLedgerAccount extends AbstractLedgerAccount implements PersonalAccount {

    public CapitalLedgerAccount(String accountId) {
        super(accountId);
    }
}
