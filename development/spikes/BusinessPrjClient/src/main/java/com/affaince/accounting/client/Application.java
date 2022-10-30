package com.affaince.accounting.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.apache.http.impl.client.HttpClients;
import org.joda.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public AccountingClient accountingClient(RestTemplate restTemplate){
        return  new AccountingClient(restTemplate);
    }
    @Bean
    public RestTemplate restTemplate() {
       HttpComponentsClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        return new RestTemplate(requestFactory);
       // return new RestTemplate();
    }

    @Bean
    public CommandLineRunner run(AccountingClient accountingClient) throws Exception {
       return args-> {
           //accountingClient.registerEventListeners();
           //accountingClient.startAccountingPeriod(new AccountingPeriodRequest("merchant1", new LocalDateTime(2023, 1, 1, 0, 0, 0), new LocalDateTime(2023, 1, 1, 23, 59, 59), AccountingPeriod.DAILY));
           execute(accountingClient);
       };
    }
    @Bean
    public ObjectMapper mapper() {
        ObjectMapper jacksonObjectMapper = new ObjectMapper();
        jacksonObjectMapper.registerModule(new JodaModule());
        return jacksonObjectMapper;
    }

    public void execute(AccountingClient accountingClient) throws Exception {
       accountingClient.registerEventListeners();
       DummyRequest request = new DummyRequest();
       request.setA("This is A");
       request.setB("This is B");
       request.setC("This is C");
       accountingClient.dummyCall(request);
       accountingClient.startAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,1,1,0,0,0),new LocalDateTime(2023,1,1,23,59,59), AccountingPeriod.DAILY));
       accountingClient.investCapital();
        accountingClient.endAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,1,1,0,0,0),new LocalDateTime(2023,1,1,23,59,59),AccountingPeriod.DAILY));

        accountingClient.startAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,1,10,0,0,0),new LocalDateTime(2023,1,10,23,59,59),AccountingPeriod.DAILY));
        accountingClient.receiveStockOfGoodsOnCredit();
        accountingClient.endAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,1,10,0,0,0),new LocalDateTime(2023,1,10,23,59,59),AccountingPeriod.DAILY));

        accountingClient.startAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,1,20,0,0,0),new LocalDateTime(2023,1,20,23,59,59),AccountingPeriod.DAILY));
        accountingClient.receiveStockOfGoodsOnPayment();
        accountingClient.paymentToSupplierInLiuOfGoods();
        accountingClient.endAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,1,20,0,0,0),new LocalDateTime(2023,1,20,23,59,59),AccountingPeriod.DAILY));

        accountingClient.startAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,1,22,0,0,0),new LocalDateTime(2023,1,22,23,59,59),AccountingPeriod.DAILY));
        accountingClient.returnOfGoodsPurchaseOnCredit();   // credit trading account 22 Jan 2023
        accountingClient.goodsDeliveredToSubscriberOnCredit();  //impact on trading account 22 Jan 2023
        accountingClient.endAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,1,22,0,0,0),new LocalDateTime(2023,1,22,23,59,59),AccountingPeriod.DAILY));

        accountingClient.startAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,1,25,0,0,0),new LocalDateTime(2023,1,25,23,59,59),AccountingPeriod.DAILY));
        accountingClient.goodsDeliveredToSubscriberOnPayment(); //impact on trading account 25 Jan 2023
        accountingClient.goodsReturnedFromSubscriber(); //impact on trading account 25 Jan 2023
        accountingClient.endAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,1,25,0,0,0),new LocalDateTime(2023,1,25,23,59,59),AccountingPeriod.DAILY));

        accountingClient.startAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,1,27,0,0,0),new LocalDateTime(2023,1,27,23,59,59),AccountingPeriod.DAILY));
        accountingClient.paymentReceivedFromSubscriber();// no impact on trading account 27 Jan 2023
        accountingClient.endAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,1,27,0,0,0),new LocalDateTime(2023,1,27,23,59,59),AccountingPeriod.DAILY));

        accountingClient.startAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,2,4,0,0,0),new LocalDateTime(2023,2,4,23,59,59),AccountingPeriod.DAILY));
        accountingClient.receiveInvoiceOfDistributionServiceAvailed(); // impact on trading account 4 Feb 2023
        accountingClient.endAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,2,4,0,0,0),new LocalDateTime(2023,2,4,23,59,59),AccountingPeriod.DAILY));

        accountingClient.startAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,2,20,0,0,0),new LocalDateTime(2023,2,20,23,59,59),AccountingPeriod.DAILY));
        accountingClient.paymentInLiuOfDistributionService();// no impact on trading account.20 Feb 2023
        accountingClient.endAccountingPeriod(new AccountingPeriodRequest("merchant1",new LocalDateTime(2023,2,20,0,0,0),new LocalDateTime(2023,2,20,23,59,59),AccountingPeriod.DAILY));

    }

/*
    public static void main(String [] args){
        PartyDatabaseSimulator.buildDatabase();
        //AccountDatabaseSimulator.buildDatabase(new LocalDateTime(2023,1,1,0,0,0),new LocalDateTime(9999,12,31,23,59,59));

        Test test = new Test();
        CommandGateway commandGateway = new CommandGateway();
        PeriodReconciliationProcessor periodReconciliationProcessor = new PeriodReconciliationProcessor();

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,1,0,0,0),new LocalDateTime(2023,1,1,23,59,59),TradingFrequency.DAILY);
        test.investCapital(commandGateway);   //does not impact trading acct --1 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,1,0,0,0),new LocalDateTime(2023,1,1,23,59,59),TradingFrequency.DAILY);


        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,10,0,0,0),new LocalDateTime(2023,1,10,23,59,59),TradingFrequency.DAILY);
        test.receiveStockOfGoodsOnCredit(commandGateway); // debit trading account 10 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,10,0,0,0),new LocalDateTime(2023,1,10,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,20,0,0,0),new LocalDateTime(2023,1,20,23,59,59),TradingFrequency.DAILY);
        test.receiveStockOfGoodsOnPayment(commandGateway);    // debit trading account 20 Jan 2023
        test.paymentToSupplierInLiuOfGoods(commandGateway);   // no impact ton trading acct 20 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,20,0,0,0),new LocalDateTime(2023,1,20,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,22,0,0,0),new LocalDateTime(2023,1,22,23,59,59),TradingFrequency.DAILY);
        test.returnOfGoodsPurchaseOnCredit(commandGateway);   // credit trading account 22 Jan 2023
        test.goodsDeliveredToSubscriberOnCredit(commandGateway);  //impact on trading account 22 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,22,0,0,0),new LocalDateTime(2023,1,22,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,25,0,0,0),new LocalDateTime(2023,1,25,23,59,59),TradingFrequency.DAILY);
        test.goodsDeliveredToSubscriberOnPayment(commandGateway); //impact on trading account 25 Jan 2023
        test.goodsReturnedFromSubscriber(commandGateway); //impact on trading account 25 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,25,0,0,0),new LocalDateTime(2023,1,25,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,1,27,0,0,0),new LocalDateTime(2023,1,27,23,59,59),TradingFrequency.DAILY);
        test.paymentReceivedFromSubscriber(commandGateway);// no impact on trading account 27 Jan 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,1,27,0,0,0),new LocalDateTime(2023,1,27,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,2,4,0,0,0),new LocalDateTime(2023,2,4,23,59,59),TradingFrequency.DAILY);
        test.receiveInvoiceOfDistributionServiceAvailed(commandGateway); // impact on trading account 4 Feb 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,2,4,0,0,0),new LocalDateTime(2023,2,4,23,59,59),TradingFrequency.DAILY);

        periodReconciliationProcessor.processStartOfPeriodOperations("merchant1",new LocalDateTime(2023,2,20,0,0,0),new LocalDateTime(2023,2,20,23,59,59),TradingFrequency.DAILY);
        test.paymentInLiuOfDistributionService(commandGateway);// no impact on trading account.20 Feb 2023
        periodReconciliationProcessor.processEndOfPeriodOperations("merchant1",new LocalDateTime(2023,2,20,0,0,0),new LocalDateTime(2023,2,20,23,59,59),TradingFrequency.DAILY);
*/

}
