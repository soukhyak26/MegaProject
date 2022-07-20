package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.PersonalAccount;

public class SupplierLedgerAccount extends AbstractLedgerAccountStereoType implements PersonalAccount {
    public SupplierLedgerAccount(String accountId) {
        super(accountId);
    }
}
