package com.affaince.accounting.pnl;

import com.affaince.accounting.trading.TradingFrequency;
import org.joda.time.LocalDateTime;

public interface ProfitAndLossAccountPostingProcessor {
    ProfitAndLossAccount postToProfitAndLossAccount(String merchantId, LocalDateTime startDateOfPeriod, LocalDateTime closureDateOfPeriod, TradingFrequency tradingFrequency);
}
