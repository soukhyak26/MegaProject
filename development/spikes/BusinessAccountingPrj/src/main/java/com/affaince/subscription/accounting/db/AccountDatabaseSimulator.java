package com.affaince.subscription.accounting.db;

import com.affaince.subscription.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.subscription.accounting.ledger.accounts.LedgerAccount;
import com.affaince.subscription.accounting.ledger.accounts.NominalAccount;
import com.affaince.subscription.accounting.ledger.accounts.PersonalAccount;
import com.affaince.subscription.accounting.ledger.accounts.RealAccount;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class AccountDatabaseSimulator {
    private final List<LedgerAccount> allAccounts;
    public AccountDatabaseSimulator(){
        allAccounts = new ArrayList<>();
    }
    public void buildDatabase(LocalDateTime startDate, LocalDateTime closureDate){
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

        LedgerAccount discountAllowedLedgerAccount = new NominalAccount("merchant1","discountAllowed",AccountIdentifier.BUSINESS_DISCOUNT_ALLOWED_ACCOUNT,startDate,closureDate);
        allAccounts.add(discountAllowedLedgerAccount);

        LedgerAccount discountReceivedLedgerAccount = new NominalAccount("merchant1","discountReceived",AccountIdentifier.BUSINESS_DISCOUNT_RECEIVED_ACCOUNT,startDate,closureDate);
        allAccounts.add(discountReceivedLedgerAccount);

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


    public void addAccount(LedgerAccount ledgerAccount){
        allAccounts.add(ledgerAccount);
    }

    public LedgerAccount searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(String merchantId, String accountId, AccountIdentifier accountIdentifier,LocalDateTime startDate,LocalDateTime closureDate) {
            return allAccounts.stream().
                    filter(account->account.getMerchantId().
                            equals(merchantId) &&
                            account.getAccountIdentifier()==accountIdentifier &&
                            account.getAccountId().equals(accountId) &&
                            (account.getStartDate().isBefore(closureDate) &&
                                    account.getClosureDate().isAfter(startDate))).
                    findAny().orElse(null);
    }

    public LedgerAccount searchActiveLedgerAccountsByAccountIdAndAccountIdentifier(String merchantId, String accountId, AccountIdentifier accountIdentifier,LocalDateTime postingDate) {
        return allAccounts.stream().
                filter(account->account.getMerchantId().
                        equals(merchantId) &&
                        account.getAccountIdentifier()==accountIdentifier &&
                        account.getAccountId().equals(accountId) &&
                        ((account.getStartDate().isEqual(postingDate) ||
                                account.getStartDate().isBefore(postingDate)) &&
                                account.getClosureDate().isAfter(postingDate))).
                findAny().orElse(null);
    }
    public List<LedgerAccount> searchActiveLedgerAccountsByAccountIdentifier(String merchantId, AccountIdentifier accountIdentifier,LocalDateTime postingDate) {
        return allAccounts.stream().
                filter(account->account.getMerchantId().equals(merchantId) &&
                        account.getAccountIdentifier()==accountIdentifier &&
                        ((account.getStartDate().isBefore(postingDate) ||
                                account.getStartDate().isEqual(postingDate)) &&
                                account.getClosureDate().isAfter(postingDate)))
                .collect(Collectors.toList());
    }

    public List<LedgerAccount> searchActiveLedgerAccountsByAccountIdentifier(String merchantId, AccountIdentifier accountIdentifier,LocalDateTime startDate,LocalDateTime closureDate) {
        return allAccounts.stream().
                filter(account->account.getMerchantId().equals(merchantId) &&
                        account.getAccountIdentifier()==accountIdentifier &&
                        ((account.getStartDate().isEqual(closureDate) ||
                                account.getStartDate().isBefore(closureDate)) &&
                                account.getClosureDate().isAfter(startDate)))
                .collect(Collectors.toList());
    }

    public List<LedgerAccount> getAllAccounts(String merchantId){
        return allAccounts.stream().filter(ac->ac.getMerchantId().equals(merchantId)).collect(Collectors.toList());
    }

    public List<LedgerAccount> getAllActiveAccounts(String merchantId,LocalDateTime startDate,LocalDateTime closureDate){
        return allAccounts.stream().
                filter(account->account.getMerchantId().equals(merchantId) &&
                        ((account.getStartDate().isEqual(closureDate) ||
                                account.getStartDate().isBefore(closureDate)) &&
                                account.getClosureDate().isAfter(startDate))).
                collect(Collectors.toList());
    }

    public LedgerAccount getLatestClosedAccount(String merchantId,String accountId,LocalDateTime startDate,LocalDateTime closureDate){
        return allAccounts.stream().filter(ac->ac.getMerchantId().equals(merchantId) &&
                ac.getAccountId().equals(accountId) &&
                ac.getLatestVersion()== true &&
                (ac.getClosureDate().isBefore(startDate)))
                .findAny().orElse(null);
    }
    public List<LedgerAccount> getAllLatestClosedAccounts(String merchantId,LocalDateTime startDate,LocalDateTime closureDate){
        return allAccounts.stream().filter(ac->ac.getMerchantId().equals(merchantId) &&
                ac.getLatestVersion()== true &&
                (ac.getClosureDate().isBefore(startDate))
        ).collect(Collectors.toList());
    }
}
