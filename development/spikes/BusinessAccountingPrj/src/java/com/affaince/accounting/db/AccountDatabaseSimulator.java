package com.affaince.accounting.db;

import com.affaince.accounting.accounts.*;
import com.affaince.accounting.accounts.types.LedgerAccount;
import com.affaince.accounting.accounts.types.NominalAccount;
import com.affaince.accounting.accounts.types.PersonalAccount;
import com.affaince.accounting.accounts.types.RealAccount;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.journal.qualifiers.AccountQualifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDatabaseSimulator {
    private static List<PersonalAccount> personalAccounts = new ArrayList<>();
    private static List<RealAccount> realAccounts = new ArrayList<>();
    private static List<NominalAccount> nominalAccounts = new ArrayList<>();

    public static void buildDatabase(){

        //external
        PersonalAccount merchantLedgerAccount = new MerchantLedgerAccount("merchant1","merchant1",AccountIdentifier.MERCHANT_ACCOUNT);
        personalAccounts.add(merchantLedgerAccount);

        //external
        PersonalAccount distributionSupplier1LedgerAccount = new ServiceProviderLedgerAccount("merchant1","distributionServiceProvider1",AccountIdentifier.DISTRIBUTION_SUPPLIER_ACCOUNT);
        personalAccounts.add(distributionSupplier1LedgerAccount);
        PersonalAccount distributionSupplier2LedgerAccount = new ServiceProviderLedgerAccount("merchant1","distributionServiceProvider2",AccountIdentifier.DISTRIBUTION_SUPPLIER_ACCOUNT);
        personalAccounts.add(distributionSupplier2LedgerAccount);

        //external
        PersonalAccount subscriber1LedgerAccount = new SubscriberLedgerAccount("merchant1","subscriber1",AccountIdentifier.SUBSCRIBER_ACCOUNT);
        personalAccounts.add(subscriber1LedgerAccount);
        PersonalAccount subscriber2LedgerAccount = new SubscriberLedgerAccount("merchant1","subscriber2",AccountIdentifier.SUBSCRIBER_ACCOUNT);
        personalAccounts.add(subscriber2LedgerAccount);
        PersonalAccount subscriber3LedgerAccount = new SubscriberLedgerAccount("merchant1","subscriber3",AccountIdentifier.SUBSCRIBER_ACCOUNT);
        personalAccounts.add(subscriber3LedgerAccount);

        //external
        PersonalAccount supplierOfProduct1LedgerAccount = new SupplierLedgerAccount("merchant1","supplierOfProduct1",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT);
        personalAccounts.add(supplierOfProduct1LedgerAccount);
        PersonalAccount supplierOfProduct2LedgerAccount = new SupplierLedgerAccount("merchant1","supplierOfProduct2",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT);
        personalAccounts.add(supplierOfProduct2LedgerAccount);
        PersonalAccount supplierOfProduct3LedgerAccount = new SupplierLedgerAccount("merchant1","supplierOfProduct3",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT);
        personalAccounts.add(supplierOfProduct3LedgerAccount);

        //business
        PersonalAccount capitalLedgerAccount = new CapitalLedgerAccount("merchant1","merchant1", AccountIdentifier.BUSINESS_CAPITAL_ACCOUNT);
        personalAccounts.add(capitalLedgerAccount);
        PersonalAccount bankLedgerAccount = new BankLedgerAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_BANK_ACCOUNT);
        personalAccounts.add(bankLedgerAccount);
        RealAccount cashLedgerAccount = new CashLedgerAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_CASH_ACCOUNT);
        realAccounts.add(cashLedgerAccount);
        RealAccount purchaseLedgerAccount = new PurchaseLedgerAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_PURCHASE_ACCOUNT);
        realAccounts.add(purchaseLedgerAccount);
        RealAccount furnitureLedgerAccount = new FurnitureLedgerAccount("merchant1","merchant1",AccountIdentifier.FIXED_ASSETS_ACCOUNT);
        realAccounts.add(furnitureLedgerAccount);

        NominalAccount discountLedgerAccount = new DiscountLedgerAccount("merchant1","merchant1",AccountIdentifier.ALL_PROFIT_AND_LOSS_ACCOUNTS);
        nominalAccounts.add(discountLedgerAccount);
        NominalAccount lossAccount = new LossAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_LOSS_ACCOUNT);
        nominalAccounts.add(lossAccount);
        NominalAccount profitAccount = new ProfitAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_PROFIT_ACCOUNT);
        nominalAccounts.add(profitAccount);
        NominalAccount rentLedgerAccount = new RentLedgerAccount("merchant1","merchant1",AccountIdentifier.OWNER_OF_PREMISE_RENTED_ACCOUNT);
        nominalAccounts.add(rentLedgerAccount);
        NominalAccount rewardLedgerAccount = new RewardsLedgerAccount("merchant1","merchant1",AccountIdentifier.SUBSCRIBER_REWARDS_ACCOUNT);
        nominalAccounts.add(rentLedgerAccount);
        NominalAccount salaryLedgerAccount = new SalaryLedgerAccount("merchant1","merchant1",AccountIdentifier.EMPLOYEE_SALARY_ACCOUNT);
        nominalAccounts.add(salaryLedgerAccount);
        NominalAccount salesAccount = new SalesAccount("merchant1","merchant1",AccountIdentifier.BUSINESS_SALES_ACCOUNT);
        nominalAccounts.add(salesAccount);
    }

    public static LedgerAccount searchLedgerAccount(String merchantId,String accountId, AccountQualifiers accountQualifier) {
        if(accountQualifier== AccountQualifiers.PERSONAL_LEDGER_ACCOUNT){
            return personalAccounts.stream().filter(personalAccount->personalAccount.getMerchantId().equals(merchantId) && personalAccount.getAccountId().equals(accountId)).findFirst().get();
        }else if(accountQualifier==AccountQualifiers.REAL_LEDGER_ACCOUNT){
            return realAccounts.stream().filter(realAccount->realAccount.getMerchantId().equals(merchantId) && realAccount.getAccountId().equals(accountId)).findFirst().get();
        }else if(accountQualifier==AccountQualifiers.NOMINAL_LEDGER_ACCOUNT){
            return nominalAccounts.stream().filter(nominalAccount->nominalAccount.getMerchantId().equals(merchantId) && nominalAccount.getAccountId().equals(accountId)).findFirst().get();
        }else{
            return null;
        }
    }

    public static List<LedgerAccount> searchLedgerAccountsByAccountIdentifier(String merchantId,AccountIdentifier accountIdentifier) {
            AccountQualifiers accountQualifier = accountIdentifier.getAccountQualifiers();
        if(accountQualifier== AccountQualifiers.PERSONAL_LEDGER_ACCOUNT){
            return personalAccounts.stream().filter(personalAccount->personalAccount.getMerchantId().equals(merchantId) && personalAccount.getAccountIdentifier()==accountIdentifier).collect(Collectors.toList());
        }else if(accountQualifier==AccountQualifiers.REAL_LEDGER_ACCOUNT){
            return realAccounts.stream().filter(realAccount->realAccount.getMerchantId().equals(merchantId) && realAccount.getAccountIdentifier()==accountIdentifier).collect(Collectors.toList());
        }else if(accountQualifier==AccountQualifiers.NOMINAL_LEDGER_ACCOUNT){
            return nominalAccounts.stream().filter(nominalAccount->nominalAccount.getMerchantId().equals(merchantId) && nominalAccount.getAccountIdentifier()==accountIdentifier).collect(Collectors.toList());
        }else{
            return null;
        }
    }

    public static List<LedgerAccount> searchLedgerAccountsByAccountIdAndAccountIdentifier(String merchantId,String accountId,AccountIdentifier accountIdentifier) {
        AccountQualifiers accountQualifier = accountIdentifier.getAccountQualifiers();
        if(accountQualifier== AccountQualifiers.PERSONAL_LEDGER_ACCOUNT){
            return personalAccounts.stream().filter(personalAccount->personalAccount.getMerchantId().equals(merchantId) && personalAccount.getAccountIdentifier()==accountIdentifier && personalAccount.getAccountId().equals(accountId)).collect(Collectors.toList());
        }else if(accountQualifier==AccountQualifiers.REAL_LEDGER_ACCOUNT){
            return realAccounts.stream().filter(realAccount->realAccount.getMerchantId().equals(merchantId) && realAccount.getAccountIdentifier()==accountIdentifier && realAccount.getAccountId().equals(accountId)).collect(Collectors.toList());
        }else if(accountQualifier==AccountQualifiers.NOMINAL_LEDGER_ACCOUNT){
            return nominalAccounts.stream().filter(nominalAccount->nominalAccount.getMerchantId().equals(merchantId) && nominalAccount.getAccountIdentifier()==accountIdentifier && nominalAccount.getAccountId().equals(accountId)).collect(Collectors.toList());
        }else{
            return null;
        }
    }

}
