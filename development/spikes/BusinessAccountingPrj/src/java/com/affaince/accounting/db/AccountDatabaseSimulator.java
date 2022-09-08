package com.affaince.accounting.db;

import com.affaince.accounting.ledger.accounts.*;
import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDatabaseSimulator {
    private static final List<LedgerAccount> allAccounts = new ArrayList<>();

    public static void buildDatabase(LocalDateTime startDate, LocalDateTime closureDate){
        //external
        LedgerAccount merchantLedgerAccount = new RealAccount("merchant1","capital",AccountIdentifier.BUSINESS_CAPITAL_ACCOUNT,startDate,closureDate);
        allAccounts.add(merchantLedgerAccount);

        //external
        LedgerAccount distributionSupplier1LedgerAccount = new PersonalAccount("merchant1","distributionServiceProvider1",AccountIdentifier.DISTRIBUTION_SUPPLIER_ACCOUNT,startDate,closureDate);
        allAccounts.add(distributionSupplier1LedgerAccount);
        LedgerAccount distributionSupplier2LedgerAccount = new PersonalAccount("merchant1","distributionServiceProvider2",AccountIdentifier.DISTRIBUTION_SUPPLIER_ACCOUNT,startDate,closureDate);
        allAccounts.add(distributionSupplier2LedgerAccount);

        //external
        LedgerAccount subscriber1LedgerAccount = new PersonalAccount("merchant1","subscriber1",AccountIdentifier.SUBSCRIBER_ACCOUNT,startDate,closureDate);
        allAccounts.add(subscriber1LedgerAccount);
        LedgerAccount subscriber2LedgerAccount = new PersonalAccount("merchant1","subscriber2",AccountIdentifier.SUBSCRIBER_ACCOUNT,startDate,closureDate);
        allAccounts.add(subscriber2LedgerAccount);
        LedgerAccount subscriber3LedgerAccount = new PersonalAccount("merchant1","subscriber3",AccountIdentifier.SUBSCRIBER_ACCOUNT,startDate,closureDate);
        allAccounts.add(subscriber3LedgerAccount);

        //external
        LedgerAccount supplierOfProduct1LedgerAccount = new PersonalAccount("merchant1","supplierOfProduct1",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT,startDate,closureDate);
        allAccounts.add(supplierOfProduct1LedgerAccount);
        LedgerAccount supplierOfProduct2LedgerAccount = new PersonalAccount("merchant1","supplierOfProduct2",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT,startDate,closureDate);
        allAccounts.add(supplierOfProduct2LedgerAccount);
        LedgerAccount supplierOfProduct3LedgerAccount = new PersonalAccount("merchant1","supplierOfProduct3",AccountIdentifier.SUPPLIER_OF_GOODS_ACCOUNT,startDate,closureDate);
        allAccounts.add(supplierOfProduct3LedgerAccount);

        //business
        LedgerAccount bankLedgerAccount = new PersonalAccount("merchant1","bank",AccountIdentifier.BUSINESS_BANK_ACCOUNT,startDate,closureDate);
        allAccounts.add(bankLedgerAccount);

        LedgerAccount cashLedgerAccount = new RealAccount("merchant1","cash",AccountIdentifier.BUSINESS_CASH_ACCOUNT,startDate,closureDate);
        allAccounts.add(cashLedgerAccount);
        LedgerAccount purchaseLedgerAccount = new RealAccount("merchant1","purchase",AccountIdentifier.BUSINESS_PURCHASE_ACCOUNT,startDate,closureDate);
        allAccounts.add(purchaseLedgerAccount);
        LedgerAccount purchaseReturnLedgerAccount = new RealAccount("merchant1","purchaseReturn",AccountIdentifier.BUSINESS_PURCHASE_RETURN_ACCOUNT,startDate,closureDate);
        allAccounts.add(purchaseReturnLedgerAccount);

        LedgerAccount furnitureLedgerAccount = new RealAccount("merchant1","furniture",AccountIdentifier.FIXED_ASSETS_ACCOUNT,startDate,closureDate);
        allAccounts.add(furnitureLedgerAccount);

        LedgerAccount discountLedgerAccount = new NominalAccount("merchant1","discount",AccountIdentifier.BUSINESS_DISCOUNT_ACCOUNT,startDate,closureDate);
        allAccounts.add(discountLedgerAccount);
        LedgerAccount lossAccount = new NominalAccount("merchant1","loss",AccountIdentifier.BUSINESS_LOSS_ACCOUNT,startDate,closureDate);
        allAccounts.add(lossAccount);
        LedgerAccount profitAccount = new NominalAccount("merchant1","profit",AccountIdentifier.BUSINESS_PROFIT_ACCOUNT,startDate,closureDate);
        allAccounts.add(profitAccount);
        LedgerAccount rentLedgerAccount = new NominalAccount("merchant1","rent",AccountIdentifier.OWNER_OF_PREMISE_RENTED_ACCOUNT,startDate,closureDate);
        allAccounts.add(rentLedgerAccount);
        LedgerAccount rewardLedgerAccount = new NominalAccount("merchant1","reward",AccountIdentifier.SUBSCRIBER_REWARDS_ACCOUNT,startDate,closureDate);
        allAccounts.add(rewardLedgerAccount);
        LedgerAccount salaryLedgerAccount = new NominalAccount("merchant1","salary",AccountIdentifier.EMPLOYEE_SALARY_ACCOUNT,startDate,closureDate);
        allAccounts.add(salaryLedgerAccount);
        LedgerAccount salesAccount = new NominalAccount("merchant1","sales",AccountIdentifier.BUSINESS_SALES_ACCOUNT,startDate,closureDate);
        allAccounts.add(salesAccount);
        LedgerAccount businessServicesAvailedAccount = new NominalAccount("merchant1","businessServices",AccountIdentifier.BUSINESS_SERVICES_AVAILED_ACCOUNT,startDate,closureDate);
        allAccounts.add(businessServicesAvailedAccount);
        LedgerAccount salesReturnedAccount = new NominalAccount("merchant1","salesReturn",AccountIdentifier.BUSINESS_SALES_RETURN_ACCOUNT,startDate,closureDate);
        allAccounts.add(salesReturnedAccount);
    }


    public static void addAccount(LedgerAccount ledgerAccount){
        allAccounts.add(ledgerAccount);
    }
    public static LedgerAccount searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(String merchantId, String accountId, AccountIdentifier accountIdentifier) {
            return allAccounts.stream().filter(account->account.getMerchantId().equals(merchantId) && account.getAccountIdentifier()==accountIdentifier && account.getAccountId().equals(accountId) && account.getClosureDate().equals(new LocalDateTime(9999,12,31,23,59,59))).findAny().orElse(null);
    }

    public static List<LedgerAccount> searchActiveLedgerAccountsByAccountIdentifier(String merchantId, AccountIdentifier accountIdentifier) {
        return allAccounts.stream().filter(account->account.getMerchantId().equals(merchantId) && account.getAccountIdentifier()==accountIdentifier && account.getClosureDate().equals(new LocalDateTime(9999,12,31,23,59,59))).collect(Collectors.toList());
    }

    public static List<LedgerAccount> getAllAccounts(String merchantId){
        return allAccounts.stream().filter(ac->ac.getMerchantId().equals(merchantId)).collect(Collectors.toList());
    }

    public static List<LedgerAccount> getAllActiveAccounts(String merchantId){
        return allAccounts.stream().filter(ac->ac.getMerchantId().equals(merchantId) && ac.getClosureDate().equals(new LocalDateTime(9999, 12, 31,23,59,59))).collect(Collectors.toList());
    }

    public static LedgerAccount getLatestClosedAccount(String merchantId,String accountId){
        return allAccounts.stream().filter(ac->ac.getMerchantId().equals(merchantId) &&
                ac.getAccountId().equals(accountId) &&
                ac.getLatestVersion()== true &&
                !ac.getClosureDate().equals(new LocalDateTime(9999, 12, 31,23,59,59))).findAny().orElse(null);
    }
    public static List<LedgerAccount> getAllLatestClosedAccounts(String merchantId){
        return allAccounts.stream().filter(ac->ac.getMerchantId().equals(merchantId) &&
                ac.getLatestVersion()== true &&
                !ac.getClosureDate().equals(new LocalDateTime(9999, 12, 31,23,59,59))).collect(Collectors.toList());
    }
}
