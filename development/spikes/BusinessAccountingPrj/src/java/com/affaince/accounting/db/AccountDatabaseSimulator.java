package com.affaince.accounting.db;

import com.affaince.accounting.ledger.accounts.*;
import com.affaince.accounting.ledger.accounts.types.LedgerAccount;
import com.affaince.accounting.ledger.accounts.types.NominalAccount;
import com.affaince.accounting.ledger.accounts.types.PersonalAccount;
import com.affaince.accounting.ledger.accounts.types.RealAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

import java.util.ArrayList;
import java.util.List;

public class AccountDatabaseSimulator {
    private static List<LedgerAccount> allAccounts = new ArrayList<>();

    public static void buildDatabase(){

        //external
        PersonalAccount merchantLedgerAccount = new MerchantLedgerAccount("merchant1","merchant1",AccountIdentifier.MERCHANT_ACCOUNT);
        allAccounts.add(merchantLedgerAccount);

        //external
        PersonalAccount distributionSupplier1LedgerAccount = new ServiceProviderLedgerAccount("merchant1","distributionServiceProvider1",AccountIdentifier.DISTRIBUTION_SUPPLIER_ACCOUNT);
        allAccounts.add(distributionSupplier1LedgerAccount);
        PersonalAccount distributionSupplier2LedgerAccount = new ServiceProviderLedgerAccount("merchant1","distributionServiceProvider2",AccountIdentifier.DISTRIBUTION_SUPPLIER_ACCOUNT);
        allAccounts.add(distributionSupplier2LedgerAccount);

        //external
        PersonalAccount subscriber1LedgerAccount = new SubscriberLedgerAccount("merchant1","subscriber1",AccountIdentifier.SUBSCRIBER_ACCOUNT);
        allAccounts.add(subscriber1LedgerAccount);
        PersonalAccount subscriber2LedgerAccount = new SubscriberLedgerAccount("merchant1","subscriber2",AccountIdentifier.SUBSCRIBER_ACCOUNT);
        allAccounts.add(subscriber2LedgerAccount);
        PersonalAccount subscriber3LedgerAccount = new SubscriberLedgerAccount("merchant1","subscriber3",AccountIdentifier.SUBSCRIBER_ACCOUNT);
        allAccounts.add(subscriber3LedgerAccount);

        //external
        PersonalAccount supplierOfProduct1LedgerAccount = new SupplierLedgerAccount("merchant1","supplierOfProduct1",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT);
        allAccounts.add(supplierOfProduct1LedgerAccount);
        PersonalAccount supplierOfProduct2LedgerAccount = new SupplierLedgerAccount("merchant1","supplierOfProduct2",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT);
        allAccounts.add(supplierOfProduct2LedgerAccount);
        PersonalAccount supplierOfProduct3LedgerAccount = new SupplierLedgerAccount("merchant1","supplierOfProduct3",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT);
        allAccounts.add(supplierOfProduct3LedgerAccount);

        //business
        PersonalAccount capitalLedgerAccount = new CapitalLedgerAccount("merchant1","merchant1", AccountIdentifier.BUSINESS_CAPITAL_ACCOUNT);
        allAccounts.add(capitalLedgerAccount);
        PersonalAccount bankLedgerAccount = new BankLedgerAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_BANK_ACCOUNT);
        allAccounts.add(bankLedgerAccount);
        RealAccount cashLedgerAccount = new CashLedgerAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_CASH_ACCOUNT);
        allAccounts.add(cashLedgerAccount);
        RealAccount purchaseLedgerAccount = new PurchaseLedgerAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_PURCHASE_ACCOUNT);
        allAccounts.add(purchaseLedgerAccount);
        RealAccount furnitureLedgerAccount = new FurnitureLedgerAccount("merchant1","merchant1",AccountIdentifier.FIXED_ASSETS_ACCOUNT);
        allAccounts.add(furnitureLedgerAccount);

        NominalAccount discountLedgerAccount = new DiscountLedgerAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_DISCOUNT_ACCOUNT);
        allAccounts.add(discountLedgerAccount);
        NominalAccount lossAccount = new LossAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_LOSS_ACCOUNT);
        allAccounts.add(lossAccount);
        NominalAccount profitAccount = new ProfitAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_PROFIT_ACCOUNT);
        allAccounts.add(profitAccount);
        NominalAccount rentLedgerAccount = new RentLedgerAccount("merchant1","merchant1",AccountIdentifier.OWNER_OF_PREMISE_RENTED_ACCOUNT);
        allAccounts.add(rentLedgerAccount);
        NominalAccount rewardLedgerAccount = new RewardsLedgerAccount("merchant1","merchant1",AccountIdentifier.SUBSCRIBER_REWARDS_ACCOUNT);
        allAccounts.add(rewardLedgerAccount);
        NominalAccount salaryLedgerAccount = new SalaryLedgerAccount("merchant1","merchant1",AccountIdentifier.EMPLOYEE_SALARY_ACCOUNT);
        allAccounts.add(salaryLedgerAccount);
        NominalAccount salesAccount = new SalesAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_SALES_ACCOUNT);
        allAccounts.add(salesAccount);
        NominalAccount businessServicesAvailedAccount = new ServicesAvailedLedgerAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_SERVICES_AVAILED_ACCOUNT);
        allAccounts.add(businessServicesAvailedAccount);
        NominalAccount goodReturnedAccount = new SalesReturnAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_SALES_RETURN_ACCOUNT);
        allAccounts.add(goodReturnedAccount);
    }


    public static LedgerAccount searchLedgerAccountsByAccountIdAndAccountIdentifier(String merchantId,String accountId,AccountIdentifier accountIdentifier) {
            return allAccounts.stream().filter(account->account.getMerchantId().equals(merchantId) && account.getAccountIdentifier()==accountIdentifier && account.getAccountId().equals(accountId)).findFirst().get();
    }

    public static List<LedgerAccount> getAllAccounts(){
        return allAccounts;
    }
}
