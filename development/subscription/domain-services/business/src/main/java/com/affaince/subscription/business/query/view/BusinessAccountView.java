package com.affaince.subscription.business.query.view;

import com.affaince.subscription.business.accounting.Account;
import com.affaince.subscription.common.type.TimeBoundMoney;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by anayonkar on 29/4/16.
 */
@Document(collection = "BusinessAccountView")
public class BusinessAccountView {
    public static final String BA_VIEW_ID = "BA_VIEW_ID";

    //TODO: Check if business account would be singleton - and hence only one id (as of now it will use year of provision date as id)
    @Id
    private final String id;
    private LocalDate dateForProvision;

    public LocalDate getDateForProvision() {
        return dateForProvision;
    }

    public void setDateForProvision(LocalDate dateForProvision) {
        this.dateForProvision = dateForProvision;
    }

    private Account purchaseCostAccount = new Account(0);
    private Account lossesAccount = new Account(0);

    //TODO: Will be received via REST controller
    private Account provisionalPurchaseCostAccount;
    private Account provisionalLossesAccount;

    public Account getLossesAccount() {
        return lossesAccount;
    }

    public void setLossesAccount(Account lossesAccount) {
        this.lossesAccount = lossesAccount;
    }

    public Account getProvisionalLossesAccount() {
        return provisionalLossesAccount;
    }

    public void setProvisionalLossesAccount(Account provisionalLossesAccount) {
        this.provisionalLossesAccount = provisionalLossesAccount;
    }

    public BusinessAccountView(String id, LocalDate dateForProvision) {
        this.id = id;
        this.dateForProvision = dateForProvision;
    }

    public void debitPurchaseCost(double currentPurchasePrice) {
        purchaseCostAccount.credit(currentPurchasePrice);
        provisionalPurchaseCostAccount.debit(currentPurchasePrice);
    }

    public Account getPurchaseCostAccount() {
        return purchaseCostAccount;
    }

    public void setPurchaseCostAccount(Account purchaseCostAccount) {
        this.purchaseCostAccount = purchaseCostAccount;
    }

    public Account getProvisionalPurchaseCostAccount() {
        return provisionalPurchaseCostAccount;
    }

    public void setProvisionalPurchaseCostAccount(Account provisionalPurchaseCostAccount) {
        this.provisionalPurchaseCostAccount = provisionalPurchaseCostAccount;
    }
}
