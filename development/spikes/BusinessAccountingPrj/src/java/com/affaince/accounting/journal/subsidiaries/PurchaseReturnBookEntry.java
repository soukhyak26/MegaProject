package com.affaince.accounting.journal.subsidiaries;

import com.affaince.accounting.journal.qualifiers.AccountIdentifier;
import com.affaince.accounting.journal.qualifiers.PartyTypes;
import com.affaince.accounting.journal.qualifiers.PriceQualifiers;
import com.affaince.accounting.transactions.TransactionEntityDetail;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseReturnBookEntry {
    private final String merchantId;
    private LocalDateTime dateOfCreditPurchaseReturn;
    private final String partyId;
    private final PartyTypes partyType;
    private final String accountId;
    private final AccountIdentifier accountIdentifier;
    private List<TransactionEntityDetail> transactionEntityDetailList;
    private String debitNoteNumber;
    private String ledgerFolioNumber;
    private Map<PriceQualifiers,Double> itemisedPrices;
    private double totalPrice;
    private String remarks;

    public static class PurchaseReturnBookEntryBuilder {
        private String merchantId;
        private LocalDateTime dateOfCreditPurchaseReturn;
        private String partyId;
        private PartyTypes partyType;
        private String accountId;
        private AccountIdentifier accountIdentifier;
        private List<TransactionEntityDetail> transactionEntityDetailList;
        private String debitNoteNumber;
        private String ledgerFolioNumber;
        private Map<PriceQualifiers,Double> itemisedPrices;
        private double totalPrice;
        private String remarks;

        public PurchaseReturnBookEntryBuilder(){
            this.transactionEntityDetailList= new ArrayList<>();
            this.itemisedPrices= new HashMap<>();
        }
        public PurchaseReturnBookEntryBuilder merchantId(String merchantId){
            this.merchantId =merchantId;
            return this;
        }

        public PurchaseReturnBookEntryBuilder dateOfCreditPurchaseReturn(LocalDateTime dateOfCreditPurchaseReturn){
            this.dateOfCreditPurchaseReturn =dateOfCreditPurchaseReturn;
            return this;
        }
        public PurchaseReturnBookEntryBuilder partyId(String partyId){
            this.partyId=partyId;
            return this;
        }
        public PurchaseReturnBookEntryBuilder partyType(PartyTypes partyType){
            this.partyType=partyType;
            return this;
        }

        public PurchaseReturnBookEntryBuilder accountId(String accountId){
            this.accountId=accountId;
            return this;
        }

        public PurchaseReturnBookEntryBuilder accountIdentifier(AccountIdentifier accountIdentifier){
            this.accountIdentifier=accountIdentifier;
            return this;
        }

        public PurchaseReturnBookEntryBuilder transactionEntityDetailList(List<TransactionEntityDetail> transactionEntityDetailList){
            this.transactionEntityDetailList=transactionEntityDetailList;
            return this;
        }

        public PurchaseReturnBookEntryBuilder debitNoteNumber(String debitNoteNumber){
            this.debitNoteNumber =debitNoteNumber;
            return this;
        }

        public PurchaseReturnBookEntryBuilder itemisedPrices(Map<PriceQualifiers,Double> itemisedPrices){
            this.itemisedPrices=itemisedPrices;
            return this;
        }

        public PurchaseReturnBookEntryBuilder totalPrice(double totalPrice){
            this.totalPrice=totalPrice;
            return this;
        }

        public PurchaseReturnBookEntryBuilder remarks(String remarks){
            this.remarks=remarks;
            return this;
        }

        public PurchaseReturnBookEntry build() {
            return PurchaseReturnBookEntry.create(this);
        }
    }


    public static PurchaseReturnBookEntryBuilder newBuilder(){
        return new PurchaseReturnBookEntryBuilder();
    }
    public static PurchaseReturnBookEntry create(PurchaseReturnBookEntryBuilder purchaseReturnBookEntryBuilder){
        return new PurchaseReturnBookEntry(purchaseReturnBookEntryBuilder);
    }
    public PurchaseReturnBookEntry(PurchaseReturnBookEntryBuilder purchaseReturnBookEntryBuilder){
        merchantId= purchaseReturnBookEntryBuilder.merchantId;
        dateOfCreditPurchaseReturn = purchaseReturnBookEntryBuilder.dateOfCreditPurchaseReturn;
        partyId= purchaseReturnBookEntryBuilder.partyId;
        partyType= purchaseReturnBookEntryBuilder.partyType;
        accountId= purchaseReturnBookEntryBuilder.accountId;
        accountIdentifier= purchaseReturnBookEntryBuilder.accountIdentifier;
        transactionEntityDetailList= purchaseReturnBookEntryBuilder.transactionEntityDetailList;
        debitNoteNumber = purchaseReturnBookEntryBuilder.debitNoteNumber;
        itemisedPrices= purchaseReturnBookEntryBuilder.itemisedPrices;
        totalPrice= purchaseReturnBookEntryBuilder.totalPrice;
        remarks= purchaseReturnBookEntryBuilder.remarks;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getPartyId() {
        return partyId;
    }

    public PartyTypes getPartyType() {
        return partyType;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountIdentifier getAccountIdentifier() {
        return accountIdentifier;
    }

    public LocalDateTime getDateOfCreditPurchaseReturn() {
        return dateOfCreditPurchaseReturn;
    }

    public List<TransactionEntityDetail> getTransactionEntityDetailList() {
        return transactionEntityDetailList;
    }

    public String getDebitNoteNumber() {
        return debitNoteNumber;
    }

    public void setLedgerFolioNumber(String ledgerFolioNumber) {
        this.ledgerFolioNumber = ledgerFolioNumber;
    }

    public String getLedgerFolioNumber() {
        return ledgerFolioNumber;
    }

    public Map<PriceQualifiers, Double> getItemisedPrices() {
        return itemisedPrices;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getRemarks() {
        return remarks;
    }

}
