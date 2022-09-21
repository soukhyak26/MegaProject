package com.affaince.accounting.trading;

import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.reconcile.AccountingPeriod;
import org.joda.time.LocalDateTime;

public interface TradingAccountPostingProcessor {

    LedgerAccount postToTradingAccount(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, AccountingPeriod accountingPeriod);

}
