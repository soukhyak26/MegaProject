package com.affaince.subscription.accounting.trading;

import com.affaince.subscription.accounting.ledger.accounts.LedgerAccount;
import com.affaince.subscription.accounting.reconcile.AccountingPeriod;
import org.joda.time.LocalDateTime;

public interface TradingAccountPostingProcessor {

    LedgerAccount postToTradingAccount(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, AccountingPeriod accountingPeriod);

}
