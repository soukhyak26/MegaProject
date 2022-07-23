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
        PersonalAccount merchantLedgerAccount = new MerchantLedgerAccount("merchant1",AccountIdentifier.MERCHANT_ACCOUNT);
        personalAccounts.add(merchantLedgerAccount);

        PersonalAccount distributionSupplier1LedgerAccount = new ServiceProviderLedgerAccount("distributionServiceProvider1",AccountIdentifier.DISTRIBUTION_SUPPLIER_ACCOUNT);
        personalAccounts.add(distributionSupplier1LedgerAccount);
        PersonalAccount distributionSupplier2LedgerAccount = new ServiceProviderLedgerAccount("distributionServiceProvider2",AccountIdentifier.DISTRIBUTION_SUPPLIER_ACCOUNT);
        personalAccounts.add(distributionSupplier2LedgerAccount);


        PersonalAccount subscriber1LedgerAccount = new SubscriberLedgerAccount("subscriber1",AccountIdentifier.SUBSCRIBER_ACCOUNT);
        personalAccounts.add(subscriber1LedgerAccount);
        PersonalAccount subscriber2LedgerAccount = new SubscriberLedgerAccount("subscriber2",AccountIdentifier.SUBSCRIBER_ACCOUNT);
        personalAccounts.add(subscriber2LedgerAccount);
        PersonalAccount subscriber3LedgerAccount = new SubscriberLedgerAccount("subscriber3",AccountIdentifier.SUBSCRIBER_ACCOUNT);
        personalAccounts.add(subscriber3LedgerAccount);


        PersonalAccount supplierOfProduct1LedgerAccount = new SupplierLedgerAccount("supplierOfProduct1",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT);
        personalAccounts.add(supplierOfProduct1LedgerAccount);
        PersonalAccount supplierOfProduct2LedgerAccount = new SupplierLedgerAccount("supplierOfProduct2",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT);
        personalAccounts.add(supplierOfProduct2LedgerAccount);
        PersonalAccount supplierOfProduct3LedgerAccount = new SupplierLedgerAccount("supplierOfProduct3",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT);
        personalAccounts.add(supplierOfProduct3LedgerAccount);


        PersonalAccount capitalLedgerAccount = new CapitalLedgerAccount("CAPITAL_LEDGER_ACCOUNT", AccountIdentifier.BUSINESS_CAPITAL_ACCOUNT);
        personalAccounts.add(capitalLedgerAccount);
        PersonalAccount bankLedgerAccount = new BankLedgerAccount("BANK_LEDGER_ACCOUNT",AccountIdentifier.BUSINESS_BANK_ACCOUNT);
        personalAccounts.add(bankLedgerAccount);
        RealAccount cashLedgerAccount = new CashLedgerAccount("CASH_LEDGER_ACCOUNT",AccountIdentifier.BUSINESS_CASH_ACCOUNT);
        realAccounts.add(cashLedgerAccount);
        RealAccount purchaseLedgerAccount = new PurchaseLedgerAccount("PURCHASE_LEDGER_ACCOUNT",AccountIdentifier.BUSINESS_PURCHASE_ACCOUNT);
        realAccounts.add(purchaseLedgerAccount);
        RealAccount furnitureLedgerAccount = new FurnitureLedgerAccount("FURNITURE_LEDGER_ACCOUNT",AccountIdentifier.FIXED_ASSETS_ACCOUNT);
        realAccounts.add(furnitureLedgerAccount);

        NominalAccount discountLedgerAccount = new DiscountLedgerAccount("DISCOUNT_LEDGER_ACCOUNT",AccountIdentifier.ALL_PROFIT_AND_LOSS_ACCOUNTS);
        nominalAccounts.add(discountLedgerAccount);
        NominalAccount lossAccount = new LossAccount("LOSS_ACCOUNT",AccountIdentifier.ALL_PROFIT_AND_LOSS_ACCOUNTS);
        nominalAccounts.add(lossAccount);
        NominalAccount profitAccount = new ProfitAccount("PROFIT_ACCOUNT",AccountIdentifier.ALL_PROFIT_AND_LOSS_ACCOUNTS);
        nominalAccounts.add(profitAccount);
        NominalAccount rentLedgerAccount = new RentLedgerAccount("RENT_LEDGER_ACCOUNT",AccountIdentifier.OWNER_OF_PREMISE_RENTED_ACCOUNT);
        nominalAccounts.add(rentLedgerAccount);
        NominalAccount rewardLedgerAccount = new RewardsLedgerAccount("REWARD_LEDGER_ACCOUNT",AccountIdentifier.SUBSCRIBER_REWARDS_ACCOUNT);
        nominalAccounts.add(rentLedgerAccount);
        NominalAccount salaryLedgerAccount = new SalaryLedgerAccount("SALARY_LEDGER_ACCOUNT",AccountIdentifier.EMPLOYEE_SALARY_ACCOUNT);
        nominalAccounts.add(salaryLedgerAccount);
        NominalAccount salesAccount = new SalesAccount("SALES_LEDGER_ACCOUNT",AccountIdentifier.BUSINESS_SALE_ACCOUNT);
        nominalAccounts.add(salesAccount);
    }

    public static LedgerAccount searchLedgerAccount(String accountId, AccountQualifiers accountQualifier) {
        if(accountQualifier== AccountQualifiers.PERSONAL_LEDGER_ACCOUNT){
            return personalAccounts.stream().filter(personalAccount->personalAccount.getAccountId().equals(accountId)).findFirst().get();
        }else if(accountQualifier==AccountQualifiers.REAL_LEDGER_ACCOUNT){
            return realAccounts.stream().filter(realAccount->realAccount.getAccountId().equals(accountId)).findFirst().get();
        }else if(accountQualifier==AccountQualifiers.NOMINAL_LEDGER_ACCOUNT){
            return nominalAccounts.stream().filter(nominalAccount->nominalAccount.getAccountId().equals(accountId)).findFirst().get();
        }else{
            return null;
        }
    }

    public static List<LedgerAccount> searchLedgerAccountsByAccountIdentifier(AccountIdentifier accountIdentifier) {
            AccountQualifiers accountQualifier = accountIdentifier.getAccountQualifiers();
        if(accountQualifier== AccountQualifiers.PERSONAL_LEDGER_ACCOUNT){
            return personalAccounts.stream().filter(personalAccount->personalAccount.getAccountIdentifier()==accountIdentifier).collect(Collectors.toList());
        }else if(accountQualifier==AccountQualifiers.REAL_LEDGER_ACCOUNT){
            return realAccounts.stream().filter(realAccount->realAccount.getAccountIdentifier()==accountIdentifier).collect(Collectors.toList());
        }else if(accountQualifier==AccountQualifiers.NOMINAL_LEDGER_ACCOUNT){
            return nominalAccounts.stream().filter(nominalAccount->nominalAccount.getAccountIdentifier()==accountIdentifier).collect(Collectors.toList());
        }else{
            return null;
        }
    }

    public static List<LedgerAccount> searchLedgerAccountsByAccountIdAndAccountIdentifier(String accountId,AccountIdentifier accountIdentifier) {
        AccountQualifiers accountQualifier = accountIdentifier.getAccountQualifiers();
        if(accountQualifier== AccountQualifiers.PERSONAL_LEDGER_ACCOUNT){
            return personalAccounts.stream().filter(personalAccount->personalAccount.getAccountIdentifier()==accountIdentifier && personalAccount.getAccountId().equals(accountId)).collect(Collectors.toList());
        }else if(accountQualifier==AccountQualifiers.REAL_LEDGER_ACCOUNT){
            return realAccounts.stream().filter(realAccount->realAccount.getAccountIdentifier()==accountIdentifier && realAccount.getAccountId().equals(accountId)).collect(Collectors.toList());
        }else if(accountQualifier==AccountQualifiers.NOMINAL_LEDGER_ACCOUNT){
            return nominalAccounts.stream().filter(nominalAccount->nominalAccount.getAccountIdentifier()==accountIdentifier && nominalAccount.getAccountId().equals(accountId)).collect(Collectors.toList());
        }else{
            return null;
        }
    }

}
