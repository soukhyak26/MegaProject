@ignore
Feature: read subscription from read side

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:read subscription
Given url subscriberReadUrl
And path 'subscription/' + id
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriptionregistration/read-subscription.json')


Scenario:read subscription creation impact from payments
Given url paymentsReadUrl
And path 'paymentaccount' + id
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriptionregistration/read-payment-account.json')
