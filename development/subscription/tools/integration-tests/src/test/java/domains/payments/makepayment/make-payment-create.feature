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
And path 'subscription/active/subscriber/' + subscriberId
When method get
Then status 200

* def subscriptionId = response.subscriptionId

Scenario:
Given url platformPaymentsUrl
And path 'payments'
And request read('classpath:domains/payments/makepayment/make-payment.json')
When method post
Then status 201
