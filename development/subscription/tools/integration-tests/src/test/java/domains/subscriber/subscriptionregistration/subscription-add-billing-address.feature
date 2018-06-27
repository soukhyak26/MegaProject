@ignore
Feature: add billing address

Scenario: Retrieve subscriber
Given url subscriberReadUrl
And path 'subscriber/name/' + 'Mandar' + '/' + 'Suresh' + '/' + 'Kulkarni' + '/' + 'Mr'
When method get
Then status 200

* def subscriberId = response.subscriberId
Scenario: Use the subscriber Id retrieved and add billing address
Given url platformSubscriberUrl
And path 'subscription/addbillingaddress/' + subscriberId
And header Accept = 'application/json'
And request read('classpath:domains/subscriber/subscriptionregistration/add-billing-address.json')
When method put
Then status 200
