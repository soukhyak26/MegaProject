package com.affaince.subscription.accounting.stock;

import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

public class OpeningStockAccount  {
    private String merchantId;
    private String accountId;
    private AccountIdentifier accountIdentifier;
    private LocalDateTime startDate;
    private LocalDateTime closureDate;
    private double balanceAmount;
    private boolean isLatestVersion;

    public OpeningStockAccount(String merchantId, String accountId,AccountIdentifier accountIdentifier, LocalDateTime startDate, LocalDateTime closureDate) {
        this.merchantId = merchantId;
        this.accountId = accountId;
        this.accountIdentifier=accountIdentifier;
        this.startDate = startDate;
        this.closureDate = closureDate;
        this.isLatestVersion=true;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(LocalDateTime closureDate) {
        this.closureDate = closureDate;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public boolean getLatestVersion() {
        return isLatestVersion;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public void addToBalanceAmount(double amount){
        this.balanceAmount += amount;
    }

    public void deductFromBalanceAccount(double amount){
        this.balanceAmount -= amount;
    }

    public void setLatestVersion(boolean isLatestVersion){
        this.isLatestVersion = isLatestVersion;
    }

    @Override
    public String toString() {
        return "OpeningStockAccount{" +
                "merchantId='" + merchantId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", accountIdentifier=" + accountIdentifier +
                ", startDate=" + startDate +
                ", closureDate=" + closureDate +
                ", balanceAmount=" + balanceAmount +
                ", isLatestVersion=" + isLatestVersion +
                '}';
    }
}
