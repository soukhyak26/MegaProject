package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.PersonalAccount;

public class SubscriberLedgerAccount extends AbstractLedgerAccountStereoType implements PersonalAccount {
    public SubscriberLedgerAccount(String accountId) {
        super(accountId);
    }
}
