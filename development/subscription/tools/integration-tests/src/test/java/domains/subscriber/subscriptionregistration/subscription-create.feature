@ignore
Feature: register a subscription

Scenario: Retrieve subscriber
Given url subscriberReadUrl
And path 'name/' + 'Mandar' + '/' + 'Suresh' + '/' + 'Kulkarni' + '/' + 'Mr'
When method get
Then status 200

* def subscriberId = response.subscriberId
Scenario: Use the subscriber Id retrieved and register subscription
Given url platformSubscriberUrl
And path 'subscription'
And request '{subscriberId : ' + subscriberId + '}'
When method post
Then status 201
