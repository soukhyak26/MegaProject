package com.affaince.accounting.trading;

import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.ledger.accounts.LedgerAccountEntry;
import com.affaince.accounting.trials.TrialBalanceEntry;
import org.joda.time.LocalDateTime;

public interface TradingAccountPostingProcessor {

    TradingAccount postToTradingAccount(String merchantId,LocalDateTime startDateOfPeriod,LocalDateTime closureDateOfPeriod,TradingFrequency tradingFrequency);

}
