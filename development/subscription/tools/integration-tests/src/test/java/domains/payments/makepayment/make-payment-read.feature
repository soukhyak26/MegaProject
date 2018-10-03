@ignore
Feature: read payment made for a subscription

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: Retrieve subscriber
Given url subscriberReadUrl
And path 'subscriber/name/' + 'Mandar' + '/' + 'Suresh' + '/' + 'Kulkarni' + '/' + 'Mr'
When method get
Then status 200

* def subscriberId = response.subscriberId

Scenario: Retrieve subscription
Given url subscriberReadUrl
And path 'subscriber/subscription/active/subscriber/' + subscriberId
When method get
Then status 200

* def subscriptionId = response.subscriptionId

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: validate payment from delivery cost account view
Given url paymentsReadUrl
And path 'payments/deliverycostaccounts/' + subscriptionId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/payments/makepayment/read-payment-deliverycostaccount.json')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: validate payment from total received cost account view
Given url paymentsReadUrl
And path 'payments/totalreceived/' + subscriptionId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/payments/makepayment/read-totalreceivedcost.json')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: validate payment from total receivable cost account view
Given url paymentsReadUrl
And path 'payments/totalreceivables/' + subscriptionId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/payments/makepayment/read-totalreceivablecost.json')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:validate payment installments
Given url subscriberReadUrl
And path 'payments/installments/' + subscriptionId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/payments/makepayment/read-paymentinstallments.json')