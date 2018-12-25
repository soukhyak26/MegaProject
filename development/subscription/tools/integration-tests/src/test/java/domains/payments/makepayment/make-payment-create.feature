@ignore
Feature: Make payment for a subscription

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

Scenario:
Given url platformPaymentsUrl
And path 'payments'
And request read('classpath:domains/payments/makepayment/make-payment.json')
When method put
Then status 200

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')
