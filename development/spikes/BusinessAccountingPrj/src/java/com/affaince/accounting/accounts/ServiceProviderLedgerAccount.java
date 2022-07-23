package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.PersonalAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class ServiceProviderLedgerAccount extends AbstractLedgerAccountStereoType implements PersonalAccount {
    public ServiceProviderLedgerAccount(String accountId, AccountIdentifier accountIdentifier) {
        super(accountId,accountIdentifier);
    }
}
