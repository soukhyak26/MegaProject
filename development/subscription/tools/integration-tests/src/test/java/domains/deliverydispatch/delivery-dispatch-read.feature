@ignore
Feature: read delivery dispatch

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: Retrieve subscriber details
Given url subscriberReadUrl
And path 'subscriber/name/' + 'Mandar' + '/' + 'Suresh' + '/' + 'Kulkarni' + '/' + 'Mr'
When method get
Then status 200

* def subscriberId = response.subscriberId

Scenario: Retrieve subscription Id given subscriber Id
Given url subscriberReadUrl
And path 'subscriber/subscription/active/subscriber/' + subscriberId
When method get
Then status 200

* def subscriptionId = response.subscriptionId

#Read on Delivery Read side
Scenario:
Given url subscriberReadUrl
And path 'subscriber/deliveries/delivered/' + subscriberId + '/' + subscriptionId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/deliverydispatch/read-delivered-deliveries.json')

#Obtain business account Id to be fed to the next scenario
Scenario:
Given url businessReadUrl
And path 'business/businessaccount'
And header Accept = 'application/json'
When method get
Then status 200

* def activeBusinessAccountId = response.id

#Read on Business- PurchaseCostAccountView
Scenario:
Given url businessReadUrl
And path 'business/purchaseaccount/' + activeBusinessAccountId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/deliverydispatch/read-business-purchasecostaccount-after.json')

#Read on Payment- TotalReceivableCostAccountView
Scenario:
Given url paymentsReadUrl
And path 'payments/totalreceivables/' + subscriptionId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/deliverydispatch/read-totalreceivablesaccount-after.json')

#Read on Payment- TotalSubscriptionCostAccountView
Scenario:
Given url paymentsReadUrl
And path 'payments/totalsubscription/' + subscriptionId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/deliverydispatch/read-totalsubscriptioncostaccount-after.json')

#Read on Payment- DeliveryCostAccountView
Scenario:
Given url paymentsReadUrl
And path 'payments/deliverycostaccount/' deliveryId + '/' + subscriberId + '/' + subscriptionId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/deliverydispatch/read-deliverycostaccount-after.json')


