package com.affaince.subscription.accounting.pnl;

import com.affaince.subscription.accounting.ledger.accounts.LedgerAccount;
import com.affaince.subscription.accounting.reconcile.AccountingPeriod;
import org.joda.time.LocalDateTime;

public interface ProfitAndLossAccountPostingProcessor {
    LedgerAccount postToProfitAndLossAccount(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, AccountingPeriod accountingPeriod);
}
