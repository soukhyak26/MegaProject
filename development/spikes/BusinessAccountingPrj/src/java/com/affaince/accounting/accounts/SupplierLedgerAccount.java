package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccount;
import com.affaince.accounting.accounts.types.PersonalAccount;

public class SupplierLedgerAccount extends AbstractLedgerAccount implements PersonalAccount {
    public SupplierLedgerAccount(String accountId) {
        super(accountId);
    }
}
