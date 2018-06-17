@ignore
Feature: register a subscription

Scenario: Retrieve subscriber
Given url subscriberReadUrl
And path 'subscriber/name/' + 'Mandar' + '/' + 'Suresh' + '/' + 'Kulkarni' + '/' + 'Mr'
When method get
Then status 200

* def subscriberId = response.subscriberId
Scenario: Use the subscriber Id retrieved and register subscription
Given url platformSubscriberUrl
And path 'subscription'
And header Accept = 'application/json'
And request read('classpath:domains/subscriber/subscriptionregistration/create-subscription.json')
When method post
Then status 201
