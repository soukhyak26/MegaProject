package com.affaince.accounting.db;

import com.affaince.accounting.accounts.*;
import com.affaince.accounting.accounts.types.LedgerAccount;
import com.affaince.accounting.accounts.types.NominalAccount;
import com.affaince.accounting.accounts.types.PersonalAccount;
import com.affaince.accounting.accounts.types.RealAccount;
import com.affaince.accounting.journal.qualifiers.AccountQualifiers;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSimulator {
    private static List<PersonalAccount> personalAccounts = new ArrayList<>();
    private static List<RealAccount> realAccounts = new ArrayList<>();
    private static List<NominalAccount> nominalAccounts = new ArrayList<>();

    public static void buildDatabase(){
        PersonalAccount capitalLedgerAccount = new CapitalLedgerAccount("CAPITAL_LEDGER_ACCOUNT");
        personalAccounts.add(capitalLedgerAccount);
        PersonalAccount merchantLedgerAccount = new MerchantLedgerAccount("MERCHANT_LEDGER_ACCOUNT");
        personalAccounts.add(merchantLedgerAccount);
        PersonalAccount bankLedgerAccount = new BankLedgerAccount("BANK_LEDGER_ACCOUNT");
        personalAccounts.add(bankLedgerAccount);
        PersonalAccount serviceProviderLedgerAccount = new ServiceProviderLedgerAccount("SERVICE_PROVIDER_LEDGER_ACCOUNT");
        personalAccounts.add(serviceProviderLedgerAccount);
        PersonalAccount subscriberLedgerAccount = new SubscriberLedgerAccount("SUBSCRIBER_LEDGER_ACCOUNT");
        personalAccounts.add(subscriberLedgerAccount);
        PersonalAccount supplierLedgerAccount = new SupplierLedgerAccount("SUPPLIER_LEDGER_ACCOUNT");
        personalAccounts.add(supplierLedgerAccount);

        RealAccount cashLedgerAccount = new CashLedgerAccount("CASH_LEDGER_ACCOUNT");
        realAccounts.add(cashLedgerAccount);
        RealAccount purchaseLedgerAccount = new PurchaseLedgerAccount("PURCHASE_LEDGER_ACCOUNT");
        realAccounts.add(purchaseLedgerAccount);
        RealAccount furnitureLedgerAccount = new FurnitureLedgerAccount("FURNITURE_LEDGER_ACCOUNT");
        realAccounts.add(furnitureLedgerAccount);

        NominalAccount discountLedgerAccount = new DiscountLedgerAccount("DISCOUNT_LEDGER_ACCOUNT");
        nominalAccounts.add(discountLedgerAccount);
        NominalAccount lossAccount = new LossAccount("LOSS_ACCOUNT");
        nominalAccounts.add(lossAccount);
        NominalAccount profitAccount = new ProfitAccount("PROFIT_ACCOUNT");
        nominalAccounts.add(profitAccount);
        NominalAccount rentLedgerAccount = new RentLedgerAccount("RENT_LEDGER_ACCOUNT");
        nominalAccounts.add(rentLedgerAccount);
        NominalAccount rewardLedgerAccount = new RewardsLedgerAccount("REWARD_LEDGER_ACCOUNT");
        nominalAccounts.add(rentLedgerAccount);
        NominalAccount salaryLedgerAccount = new SalaryLedgerAccount("SALARY_LEDGER_ACCOUNT");
        nominalAccounts.add(salaryLedgerAccount);
        NominalAccount salesAccount = new SalesAccount("SALES_LEDGER_ACCOUNT");
        nominalAccounts.add(salesAccount);
    }

    public static LedgerAccount searchLedgerAccount(String accountId, AccountQualifiers accountQualifier) {
        if(accountQualifier== AccountQualifiers.PERSONAL_LEDGER_ACCOUNT){
            return personalAccounts.stream().filter(personalAccount->personalAccount.equals(accountId)).findFirst().get();
        }else if(accountQualifier==AccountQualifiers.REAL_LEDGER_ACCOUNT){
            return realAccounts.stream().filter(personalAccount->personalAccount.equals(accountId)).findFirst().get();
        }else if(accountQualifier==AccountQualifiers.NOMINAL_LEDGER_ACCOUNT){
            return nominalAccounts.stream().filter(personalAccount->personalAccount.equals(accountId)).findFirst().get();
        }else{
            return null;
        }
    }
}
