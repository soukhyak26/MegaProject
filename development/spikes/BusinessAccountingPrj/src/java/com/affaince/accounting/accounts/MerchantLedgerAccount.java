package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.PersonalAccount;

public class MerchantLedgerAccount extends AbstractLedgerAccountStereoType implements PersonalAccount {
    public MerchantLedgerAccount(String accountId) {
        super(accountId);
    }
}
