package com.affaince.subscription.business.query.view;

import com.affaince.subscription.business.accounting.*;
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

    private PurchaseCostAccount purchaseCostAccount;
    private LossesAccount lossesAccount;
    private BenefitsAccount benefitsAccount;
    private TaxesAccount taxesAccount;
    private OthersAccount othersAccount;
    private CommonExpensesAccount commonExpensesAccount;
    private NodalAccount nodalAccount;
    private BookingAmountAccount bookingAmountAccount;
    private SubscriptionSpecificExpensesAccount subscriptionSpecificExpensesAccount;

    //TODO: Will be received via REST controller
    private PurchaseCostAccount provisionalPurchaseCostAccount;
    private LossesAccount provisionalLossesAccount;
    private BenefitsAccount provisionalBenefitsAccount;
    private TaxesAccount provisionalTaxesAccount;
    private OthersAccount provisionalOthersAccount;
    private CommonExpensesAccount provisionalCommonExpensesAccount;
    private SubscriptionSpecificExpensesAccount provisoinalSubscriptionSpecificExpensesAccount;

    private RevenueAccount revenueAccount;
    private InterestsAccount interestsGainAccount;

    public BusinessAccountView(String id, LocalDate dateForProvision) {
        this.id = id;
        this.dateForProvision = dateForProvision;

        this.purchaseCostAccount = new PurchaseCostAccount(0, dateForProvision);
        this.lossesAccount = new LossesAccount(0, dateForProvision);
        this.benefitsAccount = new BenefitsAccount(0, dateForProvision);
        this.taxesAccount = new TaxesAccount(0, dateForProvision);
        this.othersAccount = new OthersAccount(0, dateForProvision);
        this.commonExpensesAccount = new CommonExpensesAccount(0, dateForProvision);
        this.nodalAccount = new NodalAccount(0, dateForProvision);
        this.bookingAmountAccount = new BookingAmountAccount(0, dateForProvision);
        this.subscriptionSpecificExpensesAccount = new SubscriptionSpecificExpensesAccount(0, dateForProvision);

        this.revenueAccount = new RevenueAccount(0, dateForProvision);
        this.interestsGainAccount = new InterestsAccount(0, dateForProvision);
    }

    public String getId() {
        return id;
    }

    public PurchaseCostAccount getPurchaseCostAccount() {
        return purchaseCostAccount;
    }

    public LossesAccount getLossesAccount() {
        return lossesAccount;
    }

    public BenefitsAccount getBenefitsAccount() {
        return benefitsAccount;
    }

    public TaxesAccount getTaxesAccount() {
        return taxesAccount;
    }

    public OthersAccount getOthersAccount() {
        return othersAccount;
    }

    public CommonExpensesAccount getCommonExpensesAccount() {
        return commonExpensesAccount;
    }

    public NodalAccount getNodalAccount() {
        return nodalAccount;
    }

    public BookingAmountAccount getBookingAmountAccount() {
        return bookingAmountAccount;
    }

    public SubscriptionSpecificExpensesAccount getSubscriptionSpecificExpensesAccount() {
        return subscriptionSpecificExpensesAccount;
    }

    public PurchaseCostAccount getProvisionalPurchaseCostAccount() {
        return provisionalPurchaseCostAccount;
    }

    public LossesAccount getProvisionalLossesAccount() {
        return provisionalLossesAccount;
    }

    public BenefitsAccount getProvisionalBenefitsAccount() {
        return provisionalBenefitsAccount;
    }

    public TaxesAccount getProvisionalTaxesAccount() {
        return provisionalTaxesAccount;
    }

    public OthersAccount getProvisionalOthersAccount() {
        return provisionalOthersAccount;
    }

    public CommonExpensesAccount getProvisionalCommonExpensesAccount() {
        return provisionalCommonExpensesAccount;
    }

    public SubscriptionSpecificExpensesAccount getProvisoinalSubscriptionSpecificExpensesAccount() {
        return provisoinalSubscriptionSpecificExpensesAccount;
    }

    public RevenueAccount getRevenueAccount() {
        return revenueAccount;
    }

    public InterestsAccount getInterestsGainAccount() {
        return interestsGainAccount;
    }

    public void setProvisionalPurchaseCostAccount(PurchaseCostAccount provisionalPurchaseCostAccount) {
        this.provisionalPurchaseCostAccount = provisionalPurchaseCostAccount;
    }

    public void setProvisionalLossesAccount(LossesAccount provisionalLossesAccount) {
        this.provisionalLossesAccount = provisionalLossesAccount;
    }

    public void setProvisionalBenefitsAccount(BenefitsAccount provisionalBenefitsAccount) {
        this.provisionalBenefitsAccount = provisionalBenefitsAccount;
    }

    public void setProvisionalTaxesAccount(TaxesAccount provisionalTaxesAccount) {
        this.provisionalTaxesAccount = provisionalTaxesAccount;
    }

    public void setProvisionalOthersAccount(OthersAccount provisionalOthersAccount) {
        this.provisionalOthersAccount = provisionalOthersAccount;
    }

    public void setProvisionalCommonExpensesAccount(CommonExpensesAccount provisionalCommonExpensesAccount) {
        this.provisionalCommonExpensesAccount = provisionalCommonExpensesAccount;
    }

    public void setProvisoinalSubscriptionSpecificExpensesAccount(SubscriptionSpecificExpensesAccount provisoinalSubscriptionSpecificExpensesAccount) {
        this.provisoinalSubscriptionSpecificExpensesAccount = provisoinalSubscriptionSpecificExpensesAccount;
    }
}
