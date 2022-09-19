package com.affaince.accounting.pnl;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.ledger.accounts.LedgerAccount;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class ProfitAndLossAccount extends LedgerAccount {


    public ProfitAndLossAccount(String merchantId, String accountId, AccountIdentifier accountIdentifier, LocalDateTime startDate, LocalDateTime closureDate) {
        super(merchantId, accountId, accountIdentifier, startDate, closureDate);
    }

    @Override
    public String toString() {
        return "ProfitAndLossAccount{} " + super.toString();
    }
}
