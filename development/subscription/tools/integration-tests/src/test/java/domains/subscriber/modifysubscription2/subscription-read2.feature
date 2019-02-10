@ignore
Feature: read subscription from read side

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: Retrieve subscriber
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

Scenario:read subscription
Given url subscriberReadUrl
And path 'subscriber/subscription/' + subscriptionId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/modifysubscription2/read-subscription2.json')


Scenario:read subscription creation impact from payments
Given url paymentsReadUrl
And path '/payments/' + subscriptionId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/modifysubscription2/read-payment-account2.json')

Scenario:validate payment installments
Given url subscriberReadUrl
And path 'payments/installments/' + subscriptionId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/modifysubscription2/read-paymentinstallments2.json')
