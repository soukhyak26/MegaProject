package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccount;
import com.affaince.accounting.accounts.types.PersonalAccount;

public class BankLedgerAccount extends AbstractLedgerAccount implements PersonalAccount {
    public BankLedgerAccount(String accountId) {
        super(accountId);
    }
}
