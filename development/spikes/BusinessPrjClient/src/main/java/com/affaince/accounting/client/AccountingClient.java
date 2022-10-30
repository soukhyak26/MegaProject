package com.affaince.accounting.client;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;


public class AccountingClient {
    @Value("${dummyRequestUrl}")
    private String dummyRequestUrl;
    @Value("${affaince.acounting.register.listeners.url}")
    private String registerListenersUrl;
    @Value("${affaince.acounting.event.url}")
    private String accountingEventUrl;
    @Value("${affaince.acounting.period.start.url}")
    private String periodStartUrl;
    @Value("${affaince.acounting.period.end.url}")
    private String periodEndUrl;
    @Value("${affaince.acounting.journal.print.url}")
    private String printJournalUrl;
    @Value("${affaince.acounting.accounts.print.url}")
    private String printAccountsUrl;

    //private ClientHttpRequestFactory requestFactory;
    private RestTemplate restTemplate;
    @Autowired
    public AccountingClient(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
    }
    public void registerEventListeners(){
        //RestTemplate restTemplate = new RestTemplate(requestFactory);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(registerListenersUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        HttpEntity <String> entity = new  HttpEntity<>(null,headers);
        System.out.println("event listeners registered");
        try {
            restTemplate.postForEntity(builder.build().encode().toUri(),entity, Object.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void dummyCall(DummyRequest request){
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(dummyRequestUrl);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        HttpEntity<DummyRequest> entity = new HttpEntity<>(request,headers);
        try {
            restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, entity, Object.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
    public void startAccountingPeriod(AccountingPeriodRequest request){
        //RestTemplate restTemplate = new RestTemplate(requestFactory);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(periodStartUrl);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));

        HttpEntity<AccountingPeriodRequest> entity = new HttpEntity<>(request,headers);
        try {
            restTemplate.postForEntity(builder.build().encode().toUri(), entity, Object.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void endAccountingPeriod(AccountingPeriodRequest request){

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(periodEndUrl);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        HttpEntity<AccountingPeriodRequest> entity = new HttpEntity<>(request,headers);
        try {
            restTemplate.postForEntity(builder.build().encode().toUri(), entity, Object.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void printJournal(PrintAccountsRequest request){
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(printJournalUrl);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        HttpEntity<PrintAccountsRequest> entity = new HttpEntity<>(request,headers);
        try {
            restTemplate.postForEntity(builder.build().encode().toUri(), entity,String.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void printAccounts(PrintAccountsRequest request){

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(printAccountsUrl);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        HttpEntity<PrintAccountsRequest> entity = new HttpEntity<>(request,headers);
        try {
            restTemplate.postForEntity(builder.build().encode().toUri(), entity, String.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void submitAccountingEvent(AccountingTransactionRequest request) {
        System.out.println("in Business Project Client###############" + accountingEventUrl);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(accountingEventUrl);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));

        HttpEntity<AccountingTransactionRequest> entity = new HttpEntity<>(request,headers);
        try {
            restTemplate.postForEntity(builder.build().encode().toUri(), entity, String.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void investCapital(){
        AccountingTransactionRequest sourceDocument = new AccountingTransactionRequest();
                sourceDocument.setMerchantId("merchant1");
                sourceDocument.setTransactionAmount(5000000);
                sourceDocument.setDateOfTransaction(new LocalDateTime(2023,1,1,00,00,00));
                sourceDocument.setTransactionOnCredit(false);
                sourceDocument.setAccountingEvent(AccountingEvent.CAPITAL_INVESTMENT);
                sourceDocument.setGiverPartyId("merchant1");
                sourceDocument.setGiverParticipantType(PartyTypes.MERCHANT);
                sourceDocument.setExchangeableItem(ExchangeableItems.MONEY);
                sourceDocument.setGiverAmount(5000000);
                sourceDocument.setReceiverPartyId("merchant1");
                sourceDocument.setReceiverParticipantType(PartyTypes.BUSINESS);
                sourceDocument.setReceiverAmount(5000000);
        submitAccountingEvent(sourceDocument);


      /*  //capital investment
        public void investCapital(AccountingCommandGateway accountingCommandGateway){
            SourceDocument sourceDocument = SourceDocument.newBuilder()
                    .merchantId("merchant1")
                    .transactionReferenceNumber("1")
                    .transactionAmount(5000000)
                    .dateOfTransaction(new LocalDateTime(2023,1,1,00,00,00))
                    .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                    .transactionEvent(AccountingEvent.CAPITAL_INVESTMENT)
                    .giverParticipant("merchant1", PartyTypes.MERCHANT, ExchangeableItems.MONEY,5000000)
                    .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,5000000)
                    .description("capital invested")
                    .build();
            accountingCommandGateway.send(sourceDocument);*/

        }
    //stock purchase.. by default on credit
    public void receiveStockOfGoodsOnCredit(){
        AccountingTransactionRequest sourceDocument = new AccountingTransactionRequest();
        sourceDocument.setMerchantId("merchant1");
        sourceDocument.setTransactionAmount(100000);
        sourceDocument.setDateOfTransaction(new LocalDateTime(2023,1,10,00,00,00));
        sourceDocument.setTransactionOnCredit(true);
        sourceDocument.setAccountingEvent(AccountingEvent.GOODS_PURCHASE_BY_BUSINESS);
        sourceDocument.setGiverPartyId("supplierOfProduct1");
        sourceDocument.setGiverParticipantType(PartyTypes.SUPPLIER_OF_GOODS);
        sourceDocument.setExchangeableItem(ExchangeableItems.GOODS);
        sourceDocument.setGiverAmount(100000);
        sourceDocument.setReceiverPartyId("merchant1");
        sourceDocument.setReceiverParticipantType(PartyTypes.BUSINESS);
        sourceDocument.setReceiverAmount(100000);
        submitAccountingEvent(sourceDocument);

/*
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("2")
                .transactionAmount(100000)
                .dateOfTransaction(new LocalDateTime(2023,1,10,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.GOODS_PURCHASE_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,100000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000)
                .description("product X purchased on credit from supplierOfProduct1")
                .build();
        accountingCommandGateway.send(sourceDocument);
*/
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    //stock purchase.. on payment
    public void receiveStockOfGoodsOnPayment(){

        AccountingTransactionRequest sourceDocument = new AccountingTransactionRequest();
        sourceDocument.setMerchantId("merchant1");
        sourceDocument.setTransactionAmount(100000);
        sourceDocument.setDateOfTransaction(new LocalDateTime(2023,1,20,00,00,00));
        sourceDocument.setTransactionOnCredit(false);
        sourceDocument.setAccountingEvent(AccountingEvent.GOODS_PURCHASE_BY_BUSINESS);
        sourceDocument.setGiverPartyId("supplierOfProduct1");
        sourceDocument.setGiverParticipantType(PartyTypes.SUPPLIER_OF_GOODS);
        sourceDocument.setExchangeableItem(ExchangeableItems.GOODS);
        sourceDocument.setGiverAmount(100000);
        sourceDocument.setReceiverPartyId("merchant1");
        sourceDocument.setReceiverParticipantType(PartyTypes.BUSINESS);
        sourceDocument.setReceiverAmount(100000);
        submitAccountingEvent(sourceDocument);

/*
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("3")
                .transactionAmount(100000)
                .dateOfTransaction(new LocalDateTime(2023,1,20,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.GOODS_PURCHASE_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,100000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,100000)
                .description("product X purchased on payment from supplierOfProduct1")
                .build();
        accountingCommandGateway.send(sourceDocument);
*/
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //return of goods purchase on credit
    public void returnOfGoodsPurchaseOnCredit(){
        AccountingTransactionRequest sourceDocument = new AccountingTransactionRequest();
        sourceDocument.setMerchantId("merchant1");
        sourceDocument.setTransactionAmount(20000);
        sourceDocument.setDateOfTransaction(new LocalDateTime(2023,1,22,00,00,00));
        sourceDocument.setTransactionOnCredit(true);
        sourceDocument.setAccountingEvent(AccountingEvent.PURCHASE_RETURN_BY_BUSINESS);
        sourceDocument.setGiverPartyId("supplierOfProduct1");
        sourceDocument.setGiverParticipantType(PartyTypes.SUPPLIER_OF_GOODS);
        sourceDocument.setExchangeableItem(ExchangeableItems.GOODS);
        sourceDocument.setGiverAmount(20000);
        sourceDocument.setReceiverPartyId("merchant1");
        sourceDocument.setReceiverParticipantType(PartyTypes.BUSINESS);
        sourceDocument.setReceiverAmount(20000);
        submitAccountingEvent(sourceDocument);

/*
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("4")
                .transactionAmount(20000)
                .dateOfTransaction(new LocalDateTime(2023,1,22,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.PURCHASE_RETURN_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,20000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,20000)
                .description("product X purchased from supplierOfProduct1 is returned")
                .build();
        accountingCommandGateway.send(sourceDocument);
*/
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    // supplier payment
    public void paymentToSupplierInLiuOfGoods(){
        AccountingTransactionRequest sourceDocument = new AccountingTransactionRequest();
        sourceDocument.setMerchantId("merchant1");
        sourceDocument.setTransactionAmount(80000);
        sourceDocument.setDateOfTransaction(new LocalDateTime(2023,1,20,00,00,00));
        sourceDocument.setTransactionOnCredit(false);
        sourceDocument.setAccountingEvent(AccountingEvent.PAYMENT_MADE_TO_SUPPLIER);
        sourceDocument.setGiverPartyId("merchant1");
        sourceDocument.setGiverParticipantType(PartyTypes.BUSINESS);
        sourceDocument.setExchangeableItem(ExchangeableItems.MONEY);
        sourceDocument.setGiverAmount(80000);
        sourceDocument.setReceiverPartyId("supplierOfProduct1");
        sourceDocument.setReceiverParticipantType(PartyTypes.SUPPLIER_OF_GOODS);
        sourceDocument.setReceiverAmount(80000);
        submitAccountingEvent(sourceDocument);

/*
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("3")
                .transactionAmount(80000)
                .dateOfTransaction(new LocalDateTime(2023,1,20,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.PAYMENT_MADE_TO_SUPPLIER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,80000)
                .receiverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.MONEY,80000)
                .description("payment to the supplier")
                .build();
        accountingCommandGateway.send(sourceDocument);
*/
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

/*
    //return of goods purchase on credit
    public void paymentReturnBySupplierInLiuOfReturnOfGoodsPurchaseO(){
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("4")
                .transactionAmount(20000)
                .dateOfTransaction(new LocalDateTime(2023,2,2,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(TransactionEvents.PURCHASE_RETURN_BY_BUSINESS)
                .giverParticipant("supplierOfProduct1", PartyTypes.SUPPLIER_OF_GOODS, ExchangeableItems.GOODS,20000)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,20000)
                .description("product X purchased from supplierOfProduct1 is returned.Supplier has returned amount of returned goods")
                .build();
        processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
*/

    //distribution services availed.. on credit by default
    //beneficiary is business,giver is service provider
    public void receiveInvoiceOfDistributionServiceAvailed(){
        AccountingTransactionRequest sourceDocument = new AccountingTransactionRequest();
        sourceDocument.setMerchantId("merchant1");
        sourceDocument.setTransactionAmount(600);
        sourceDocument.setDateOfTransaction(new LocalDateTime(2023,2,4,00,00,00));
        sourceDocument.setTransactionOnCredit(true);
        sourceDocument.setAccountingEvent(AccountingEvent.SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER);
        sourceDocument.setGiverPartyId("distributionServiceProvider1");
        sourceDocument.setGiverParticipantType(PartyTypes.DISTRIBUTION_SUPPLIER);
        sourceDocument.setExchangeableItem(ExchangeableItems.SERVICE);
        sourceDocument.setGiverAmount(600);
        sourceDocument.setReceiverPartyId("merchant1");
        sourceDocument.setReceiverParticipantType(PartyTypes.BUSINESS);
        sourceDocument.setReceiverAmount(600);
        submitAccountingEvent(sourceDocument);

/*
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("4")
                .transactionAmount(600)
                .dateOfTransaction(new LocalDateTime(2023,2,4,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.SERVICE_INVOICE_RECEIVED_FROM_SERVICE_PROVIDER)
                .giverParticipant("distributionServiceProvider1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.SERVICE,600)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,600)
                .description("capital invested")
                .build();
        accountingCommandGateway.send(sourceDocument);
*/
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //payment to distribution supplier
    public void paymentInLiuOfDistributionService(){
        AccountingTransactionRequest sourceDocument = new AccountingTransactionRequest();
        sourceDocument.setMerchantId("merchant1");
        sourceDocument.setTransactionAmount(600);
        sourceDocument.setDateOfTransaction(new LocalDateTime(2023,2,20,00,00,00));
        sourceDocument.setTransactionOnCredit(false);
        sourceDocument.setAccountingEvent(AccountingEvent.PAYMENT_MADE_TO_SERVICE_PROVIDER);
        sourceDocument.setGiverPartyId("merchant1");
        sourceDocument.setGiverParticipantType(PartyTypes.BUSINESS);
        sourceDocument.setExchangeableItem(ExchangeableItems.MONEY);
        sourceDocument.setGiverAmount(600);
        sourceDocument.setReceiverPartyId("distributionServiceProvider1");
        sourceDocument.setReceiverParticipantType(PartyTypes.DISTRIBUTION_SUPPLIER);
        sourceDocument.setReceiverAmount(600);
        submitAccountingEvent(sourceDocument);

/*
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("5")
                .transactionAmount(600)
                .dateOfTransaction(new LocalDateTime(2023,2,20,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.PAYMENT_MADE_TO_SERVICE_PROVIDER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,600)
                .receiverParticipant("distributionServiceProvider1", PartyTypes.DISTRIBUTION_SUPPLIER, ExchangeableItems.MONEY,600)
                .description("capital invested")
                .build();
        accountingCommandGateway.send(sourceDocument);
*/
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    public void goodsDeliveredToSubscriberOnCredit(){

        AccountingTransactionRequest sourceDocument = new AccountingTransactionRequest();
        sourceDocument.setMerchantId("merchant1");
        sourceDocument.setTransactionAmount(1000);
        sourceDocument.setDateOfTransaction(new LocalDateTime(2023,1,22,00,00,00));
        sourceDocument.setTransactionOnCredit(true);
        sourceDocument.setAccountingEvent(AccountingEvent.GOODS_DELIVERY_TO_SUBSCRIBER);
        sourceDocument.setGiverPartyId("merchant1");
        sourceDocument.setGiverParticipantType(PartyTypes.BUSINESS);
        sourceDocument.setExchangeableItem(ExchangeableItems.GOODS);
        sourceDocument.setGiverAmount(800);
        sourceDocument.setReceiverPartyId("subscriber1");
        sourceDocument.setReceiverParticipantType(PartyTypes.SUBSCRIBER);
        sourceDocument.setReceiverAmount(1000);
        submitAccountingEvent(sourceDocument);

/*
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("7")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2023,1,22,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.GOODS_DELIVERY_TO_SUBSCRIBER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.GOODS,800)
                .receiverParticipant("subscriber1", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,1000)
                .description("goods delivery to subscriber on credit")
                .build();
        accountingCommandGateway.send(sourceDocument);
*/
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    public void goodsDeliveredToSubscriberOnPayment(){

        AccountingTransactionRequest sourceDocument = new AccountingTransactionRequest();
        sourceDocument.setMerchantId("merchant1");
        sourceDocument.setTransactionAmount(1000);
        sourceDocument.setDateOfTransaction(new LocalDateTime(2023,1,25,00,00,00));
        sourceDocument.setTransactionOnCredit(false);
        sourceDocument.setAccountingEvent(AccountingEvent.GOODS_DELIVERY_TO_SUBSCRIBER);
        sourceDocument.setGiverPartyId("merchant1");
        sourceDocument.setGiverParticipantType(PartyTypes.BUSINESS);
        sourceDocument.setExchangeableItem(ExchangeableItems.GOODS);
        sourceDocument.setGiverAmount(800);
        sourceDocument.setReceiverPartyId("subscriber2");
        sourceDocument.setReceiverParticipantType(PartyTypes.SUBSCRIBER);
        sourceDocument.setReceiverAmount(1000);
        submitAccountingEvent(sourceDocument);

/*
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("8")
                .transactionAmount(1000)
                .dateOfTransaction(new LocalDateTime(2023,1,25,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.GOODS_DELIVERY_TO_SUBSCRIBER)
                .giverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.GOODS,800)
                .receiverParticipant("subscriber2", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,1000)
                .description("goods delivery to subscriber on payment")
                .build();
        accountingCommandGateway.send(sourceDocument);
*/
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    //corresponding payment may have been received or some part may be due
    //for now lets assume that payment of the delivered goods is already received.
    public void goodsReturnedFromSubscriber(){
        AccountingTransactionRequest sourceDocument = new AccountingTransactionRequest();
        sourceDocument.setMerchantId("merchant1");
        sourceDocument.setTransactionAmount(200);
        sourceDocument.setDateOfTransaction(new LocalDateTime(2023,1,25,00,00,00));
        sourceDocument.setTransactionOnCredit(true);
        sourceDocument.setAccountingEvent(AccountingEvent.GOODS_RETURN_FROM_SUBSCRIBER);
        sourceDocument.setGiverPartyId("subscriber1");
        sourceDocument.setGiverParticipantType(PartyTypes.SUBSCRIBER);
        sourceDocument.setExchangeableItem(ExchangeableItems.GOODS);
        sourceDocument.setGiverAmount(200);
        sourceDocument.setReceiverPartyId("merchant1");
        sourceDocument.setReceiverParticipantType(PartyTypes.BUSINESS);
        sourceDocument.setReceiverAmount(200);
        submitAccountingEvent(sourceDocument);

/*
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("8")
                .transactionAmount(200)
                .dateOfTransaction(new LocalDateTime(2023,1,25,00,00,00))
                .modeOfTransaction(ModeOfTransaction.ON_CREDIT)
                .transactionEvent(AccountingEvent.GOODS_RETURN_FROM_SUBSCRIBER)
                .giverParticipant("subscriber1", PartyTypes.SUBSCRIBER, ExchangeableItems.GOODS,200)
                .receiverParticipant("merchant1", PartyTypes.BUSINESS, ExchangeableItems.GOODS,200)
                .description("goods returned by subscriber (purchased on credit) ")
                .build();
        accountingCommandGateway.send(sourceDocument);
*/
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }
    //payment received from subscriber.. it may include advance as well as payment for the goods received.
    //as of now lets assume that it makes the business debtor
    public void paymentReceivedFromSubscriber(){

        AccountingTransactionRequest sourceDocument = new AccountingTransactionRequest();
        sourceDocument.setMerchantId("merchant1");
        sourceDocument.setTransactionAmount(800);
        sourceDocument.setDateOfTransaction(new LocalDateTime(2023,1,27,00,00,00));
        sourceDocument.setTransactionOnCredit(false);
        sourceDocument.setAccountingEvent(AccountingEvent.PAYMENT_RECEIVED_FROM_SUBSCRIBER);
        sourceDocument.setGiverPartyId("subscriber1");
        sourceDocument.setGiverParticipantType(PartyTypes.SUBSCRIBER);
        sourceDocument.setExchangeableItem(ExchangeableItems.MONEY);
        sourceDocument.setGiverAmount(800);
        sourceDocument.setReceiverPartyId("merchant1");
        sourceDocument.setReceiverParticipantType(PartyTypes.BUSINESS);
        sourceDocument.setReceiverAmount(800);
        submitAccountingEvent(sourceDocument);

/*
        SourceDocument sourceDocument = SourceDocument.newBuilder()
                .merchantId("merchant1")
                .transactionReferenceNumber("6")
                .transactionAmount(800)
                .dateOfTransaction(new LocalDateTime(2023,1,27,00,00,00))
                .modeOfTransaction(ModeOfTransaction.BY_PAYMENT)
                .transactionEvent(AccountingEvent.PAYMENT_RECEIVED_FROM_SUBSCRIBER)
                .giverParticipant("subscriber1", PartyTypes.SUBSCRIBER,ExchangeableItems.MONEY,800)
                .receiverParticipant("merchant1",PartyTypes.BUSINESS,ExchangeableItems.MONEY,800)
                .description("payment made by subscriber1")
                .build();
        accountingCommandGateway.send(sourceDocument);
*/
        //processJournalLedgerAndSubsidiaryBooks(sourceDocument);
    }

}
