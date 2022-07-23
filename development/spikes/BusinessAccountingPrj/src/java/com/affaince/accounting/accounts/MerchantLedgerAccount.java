package com.affaince.accounting.accounts;

import com.affaince.accounting.accounts.types.AbstractLedgerAccountStereoType;
import com.affaince.accounting.accounts.types.PersonalAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

public class MerchantLedgerAccount extends AbstractLedgerAccountStereoType implements PersonalAccount {
    public MerchantLedgerAccount(String accountId, AccountIdentifier accountIdentifier) {
        super(accountId,accountIdentifier);
    }
}
