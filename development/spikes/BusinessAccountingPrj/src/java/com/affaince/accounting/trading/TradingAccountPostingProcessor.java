package com.affaince.accounting.trading;

import org.joda.time.LocalDateTime;

public interface TradingAccountPostingProcessor {

    TradingAccount postToTradingAccount(String merchantId,LocalDateTime startDateOfPeriod,LocalDateTime closureDateOfPeriod,TradingFrequency tradingFrequency);

}
