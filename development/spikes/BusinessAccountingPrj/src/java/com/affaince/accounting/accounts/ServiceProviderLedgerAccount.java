package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.PersonalAccount;

public class ServiceProviderLedgerAccount extends AbstractLedgerAccountStereoType implements PersonalAccount {
    public ServiceProviderLedgerAccount(String accountId) {
        super(accountId);
    }
}
