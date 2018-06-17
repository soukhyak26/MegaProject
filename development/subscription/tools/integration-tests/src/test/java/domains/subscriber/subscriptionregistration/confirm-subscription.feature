@ignore
Feature: confirm subscription

Scenario: Retrieve subscriber
Given url subscriberReadUrl
And path 'subscriber/name/' + 'Mandar' + '/' + 'Suresh' + '/' + 'Kulkarni' + '/' + 'Mr'
When method get
Then status 200

* def subscriberId = response.subscriberId

Scenario: confirm subscription
Given url platformSubscriberUrl
And path 'subscription/confirmsubscription/' + subscriberId
And request {}
When method put
Then status 200
