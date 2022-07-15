package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccount;
import com.affaince.accounting.accounts.types.PersonalAccount;

public class ServiceProviderLedgerAccount extends AbstractLedgerAccount implements PersonalAccount {
    public ServiceProviderLedgerAccount(String accountId) {
        super(accountId);
    }
}
