package com.affaince.subscription.business.query.view;

import com.affaince.subscription.business.accounting.Account;
import com.affaince.subscription.business.provision.ProvisionIndex;
import com.affaince.subscription.common.type.TimeBoundMoney;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anayonkar on 29/4/16.
 */
@Document(collection = "BusinessAccountView")
public class BusinessAccountView {
    @Id
    private final String id;
    private LocalDate dateForProvision;

    public LocalDate getDateForProvision() {
        return dateForProvision;
    }

    public void setDateForProvision(LocalDate dateForProvision) {
        this.dateForProvision = dateForProvision;
    }

    private List<Account> accountList = new ArrayList<>(ProvisionIndex.MAX_CAPACITY.getIndex());
    private List<Account> provisionalAccountList = new ArrayList<>(ProvisionIndex.MAX_CAPACITY.getIndex());

    {
        for(int i = 0; i < ProvisionIndex.MAX_CAPACITY.getIndex() ; i++) {
            accountList.add(i, new Account(0));
        }
    }

    public BusinessAccountView(String id, LocalDate dateForProvision) {
        this.id = id;
        this.dateForProvision = dateForProvision;
    }

    public void debitPurchaseCost(double currentPurchasePrice) {
        accountList.get(ProvisionIndex.PURCHASE_COST.getIndex()).credit(currentPurchasePrice);
        provisionalAccountList.get(ProvisionIndex.PURCHASE_COST.getIndex()).debit(currentPurchasePrice);
    }

    public String getId() {
        return id;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public List<Account> getProvisionalAccountList() {
        return provisionalAccountList;
    }

}
