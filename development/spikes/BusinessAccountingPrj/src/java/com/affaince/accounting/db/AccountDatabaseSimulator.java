package com.affaince.accounting.db;

import com.affaince.accounting.ledger.accounts.*;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDatabaseSimulator {
    private static final List<LedgerAccount> allAccounts = new ArrayList<>();

    public static void buildDatabase(){

        //external
        LedgerAccount merchantLedgerAccount = new PersonalAccount("merchant1","merchant1",AccountIdentifier.MERCHANT_ACCOUNT);
        allAccounts.add(merchantLedgerAccount);

        //external
        LedgerAccount distributionSupplier1LedgerAccount = new PersonalAccount("merchant1","distributionServiceProvider1",AccountIdentifier.DISTRIBUTION_SUPPLIER_ACCOUNT);
        allAccounts.add(distributionSupplier1LedgerAccount);
        LedgerAccount distributionSupplier2LedgerAccount = new PersonalAccount("merchant1","distributionServiceProvider2",AccountIdentifier.DISTRIBUTION_SUPPLIER_ACCOUNT);
        allAccounts.add(distributionSupplier2LedgerAccount);

        //external
        LedgerAccount subscriber1LedgerAccount = new PersonalAccount("merchant1","subscriber1",AccountIdentifier.SUBSCRIBER_ACCOUNT);
        allAccounts.add(subscriber1LedgerAccount);
        LedgerAccount subscriber2LedgerAccount = new PersonalAccount("merchant1","subscriber2",AccountIdentifier.SUBSCRIBER_ACCOUNT);
        allAccounts.add(subscriber2LedgerAccount);
        LedgerAccount subscriber3LedgerAccount = new PersonalAccount("merchant1","subscriber3",AccountIdentifier.SUBSCRIBER_ACCOUNT);
        allAccounts.add(subscriber3LedgerAccount);

        //external
        LedgerAccount supplierOfProduct1LedgerAccount = new PersonalAccount("merchant1","supplierOfProduct1",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT);
        allAccounts.add(supplierOfProduct1LedgerAccount);
        LedgerAccount supplierOfProduct2LedgerAccount = new PersonalAccount("merchant1","supplierOfProduct2",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT);
        allAccounts.add(supplierOfProduct2LedgerAccount);
        LedgerAccount supplierOfProduct3LedgerAccount = new PersonalAccount("merchant1","supplierOfProduct3",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT);
        allAccounts.add(supplierOfProduct3LedgerAccount);

        //business
        LedgerAccount capitalLedgerAccount = new PersonalAccount("merchant1","capital", AccountIdentifier.BUSINESS_CAPITAL_ACCOUNT);
        allAccounts.add(capitalLedgerAccount);
        LedgerAccount bankLedgerAccount = new PersonalAccount("merchant1","bank",AccountIdentifier.BUSINESS_BANK_ACCOUNT);
        allAccounts.add(bankLedgerAccount);

        LedgerAccount cashLedgerAccount = new RealAccount("merchant1","cash",AccountIdentifier.BUSINESS_CASH_ACCOUNT);
        allAccounts.add(cashLedgerAccount);
        LedgerAccount purchaseLedgerAccount = new RealAccount("merchant1","purchase",AccountIdentifier.BUSINESS_PURCHASE_ACCOUNT);
        allAccounts.add(purchaseLedgerAccount);
        LedgerAccount purchaseReturnLedgerAccount = new RealAccount("merchant1","purchaseReturn",AccountIdentifier.BUSINESS_PURCHASE_RETURN_ACCOUNT);
        allAccounts.add(purchaseReturnLedgerAccount);

        LedgerAccount furnitureLedgerAccount = new RealAccount("merchant1","furniture",AccountIdentifier.FIXED_ASSETS_ACCOUNT);
        allAccounts.add(furnitureLedgerAccount);

        LedgerAccount discountLedgerAccount = new NominalAccount("merchant1","discount",AccountIdentifier.BUSINESS_DISCOUNT_ACCOUNT);
        allAccounts.add(discountLedgerAccount);
        LedgerAccount lossAccount = new NominalAccount("merchant1","loss",AccountIdentifier.BUSINESS_LOSS_ACCOUNT);
        allAccounts.add(lossAccount);
        LedgerAccount profitAccount = new NominalAccount("merchant1","profit",AccountIdentifier.BUSINESS_PROFIT_ACCOUNT);
        allAccounts.add(profitAccount);
        LedgerAccount rentLedgerAccount = new NominalAccount("merchant1","rent",AccountIdentifier.OWNER_OF_PREMISE_RENTED_ACCOUNT);
        allAccounts.add(rentLedgerAccount);
        LedgerAccount rewardLedgerAccount = new NominalAccount("merchant1","reward",AccountIdentifier.SUBSCRIBER_REWARDS_ACCOUNT);
        allAccounts.add(rewardLedgerAccount);
        LedgerAccount salaryLedgerAccount = new NominalAccount("merchant1","salary",AccountIdentifier.EMPLOYEE_SALARY_ACCOUNT);
        allAccounts.add(salaryLedgerAccount);
        LedgerAccount salesAccount = new NominalAccount("merchant1","sales",AccountIdentifier.BUSINESS_SALES_ACCOUNT);
        allAccounts.add(salesAccount);
        LedgerAccount businessServicesAvailedAccount = new NominalAccount("merchant1","businessServices",AccountIdentifier.BUSINESS_SERVICES_AVAILED_ACCOUNT);
        allAccounts.add(businessServicesAvailedAccount);
        LedgerAccount salesReturnedAccount = new NominalAccount("merchant1","salesReturn",AccountIdentifier.BUSINESS_SALES_RETURN_ACCOUNT);
        allAccounts.add(salesReturnedAccount);
    }


    public static LedgerAccount searchLedgerAccountsByAccountIdAndAccountIdentifier(String merchantId,String accountId,AccountIdentifier accountIdentifier) {
            return allAccounts.stream().filter(account->account.getMerchantId().equals(merchantId) && account.getAccountIdentifier()==accountIdentifier && account.getAccountId().equals(accountId)).findFirst().get();
    }

    public static List<LedgerAccount> searchLedgerAccountsByAccountIdentifier(String merchantId,AccountIdentifier accountIdentifier) {
        return allAccounts.stream().filter(account->account.getMerchantId().equals(merchantId) && account.getAccountIdentifier()==accountIdentifier).collect(Collectors.toList());
    }

    public static List<LedgerAccount> getAllAccounts(){
        return allAccounts;
    }
}
