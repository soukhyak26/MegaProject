@ignore
Feature: select payment scheme

Scenario: Retrieve subscriber
Given url subscriberReadUrl
And path 'subscriber/name/' + 'Mandar' + '/' + 'Suresh' + '/' + 'Kulkarni' + '/' + 'Mr'
When method get
Then status 200

* def subscriberId = response.subscriberId

Scenario: Retrieve Payment Scheme
Given url subscriberReadUrl
And path 'subscriber/payments/schemes'
When method get
Then status 200

* def schemeId = response[0].schemeId
* print schemeId

Scenario: select payment scheme
Given url platformSubscriberUrl
And path 'subscription/selectpayment/' + subscriberId
And request read('classpath:domains/subscriber/subscriptionregistration/payment-scheme-select.json')
When method put
Then status 200
