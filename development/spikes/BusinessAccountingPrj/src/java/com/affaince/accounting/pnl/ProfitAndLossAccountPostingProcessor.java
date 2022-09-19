package com.affaince.accounting.pnl;

import com.affaince.accounting.ledger.accounts.LedgerAccount;
import com.affaince.accounting.trading.TradingFrequency;
import org.joda.time.LocalDateTime;

public interface ProfitAndLossAccountPostingProcessor {
    LedgerAccount postToProfitAndLossAccount(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, TradingFrequency tradingFrequency);
}
