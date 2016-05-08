package com.affaince.subscription.business.query.view;

import com.affaince.subscription.business.accounting.Account;
import com.affaince.subscription.business.accounting.AccountType;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by anayonkar on 29/4/16.
 */
@Document(collection = "BusinessAccountView")
public class BusinessAccountView {

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

    private Account purchaseCostAccount = new Account(AccountType.PURCHASE_COST, 0);
    private Account lossesAccount = new Account(AccountType.LOSSES, 0);
    private Account benefitsAccount = new Account(AccountType.BENEFITS, 0);
    private Account taxesAccount = new Account(AccountType.TAXES, 0);
    private Account othersAccount = new Account(AccountType.OTHERS, 0);
    private Account commonExpensesAccount = new Account(AccountType.COMMON_EXPENSES, 0);
    private Account nodalAccount = new Account(AccountType.NODAL_ACCOUNT, 0);
    private Account bookingAmountAccount = new Account(AccountType.BOOKING_AMOUNT, 0);
    private Account subscriptionSpecificExpensesAccount = new Account(AccountType.SUBSCRIPTION_SPECIFIC_EXPENSES, 0);

    //TODO: Will be received via REST controller
    private Account provisionalPurchaseCostAccount;
    private Account provisionalLossesAccount;
    private Account provisionalBenefitsAccount;
    private Account provisionalTaxesAccount;
    private Account provisionalOthersAccount;
    private Account provisionalCommonExpensesAccount;
    private Account provisoinalSubscriptionSpecificExpensesAccount;

    private Account revenueAccount = new Account(AccountType.REVENUE, 0);
    private Account interestsGainAccount = new Account(AccountType.INTERESTS, 0);

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

    /*public void debitPurchaseCost(double currentPurchasePrice) {
        purchaseCostAccount.credit(currentPurchasePrice);
        provisionalPurchaseCostAccount.debit(currentPurchasePrice);
    }*/

    public Account getPurchaseCostAccount() {
        return purchaseCostAccount;
    }

    public void setPurchaseCostAccount(Account purchaseCostAccount) {
        this.purchaseCostAccount = purchaseCostAccount;
    }

    public Account getBenefitsAccount() {
        return benefitsAccount;
    }

    public void setBenefitsAccount(Account benefitsAccount) {
        this.benefitsAccount = benefitsAccount;
    }

    public Account getTaxesAccount() {
        return taxesAccount;
    }

    public void setTaxesAccount(Account taxesAccount) {
        this.taxesAccount = taxesAccount;
    }

    public Account getOthersAccount() {
        return othersAccount;
    }

    public void setOthersAccount(Account othersAccount) {
        this.othersAccount = othersAccount;
    }

    public Account getCommonExpensesAccount() {
        return commonExpensesAccount;
    }

    public void setCommonExpensesAccount(Account commonExpensesAccount) {
        this.commonExpensesAccount = commonExpensesAccount;
    }

    public Account getNodalAccount() {
        return nodalAccount;
    }

    public void setNodalAccount(Account nodalAccount) {
        this.nodalAccount = nodalAccount;
    }

    public Account getBookingAmountAccount() {
        return bookingAmountAccount;
    }

    public void setBookingAmountAccount(Account bookingAmountAccount) {
        this.bookingAmountAccount = bookingAmountAccount;
    }

    public Account getSubscriptionSpecificExpensesAccount() {
        return subscriptionSpecificExpensesAccount;
    }

    public void setSubscriptionSpecificExpensesAccount(Account subscriptionSpecificExpensesAccount) {
        this.subscriptionSpecificExpensesAccount = subscriptionSpecificExpensesAccount;
    }

    public Account getProvisionalPurchaseCostAccount() {
        return provisionalPurchaseCostAccount;
    }

    public void setProvisionalPurchaseCostAccount(Account provisionalPurchaseCostAccount) {
        this.provisionalPurchaseCostAccount = provisionalPurchaseCostAccount;
    }

    public Account getProvisionalBenefitsAccount() {
        return provisionalBenefitsAccount;
    }

    public void setProvisionalBenefitsAccount(Account provisionalBenefitsAccount) {
        this.provisionalBenefitsAccount = provisionalBenefitsAccount;
    }

    public Account getProvisionalTaxesAccount() {
        return provisionalTaxesAccount;
    }

    public void setProvisionalTaxesAccount(Account provisionalTaxesAccount) {
        this.provisionalTaxesAccount = provisionalTaxesAccount;
    }

    public Account getProvisionalOthersAccount() {
        return provisionalOthersAccount;
    }

    public void setProvisionalOthersAccount(Account provisionalOthersAccount) {
        this.provisionalOthersAccount = provisionalOthersAccount;
    }

    public Account getProvisionalCommonExpensesAccount() {
        return provisionalCommonExpensesAccount;
    }

    public void setProvisionalCommonExpensesAccount(Account provisionalCommonExpensesAccount) {
        this.provisionalCommonExpensesAccount = provisionalCommonExpensesAccount;
    }

    public Account getProvisoinalSubscriptionSpecificExpensesAccount() {
        return provisoinalSubscriptionSpecificExpensesAccount;
    }

    public void setProvisoinalSubscriptionSpecificExpensesAccount(Account provisoinalSubscriptionSpecificExpensesAccount) {
        this.provisoinalSubscriptionSpecificExpensesAccount = provisoinalSubscriptionSpecificExpensesAccount;
    }

    public Account getRevenueAccount() {
        return revenueAccount;
    }

    public void setRevenueAccount(Account revenueAccount) {
        this.revenueAccount = revenueAccount;
    }

    public Account getInterestsGainAccount() {
        return interestsGainAccount;
    }

    public void setInterestsGainAccount(Account interestsGainAccount) {
        this.interestsGainAccount = interestsGainAccount;
    }
}
