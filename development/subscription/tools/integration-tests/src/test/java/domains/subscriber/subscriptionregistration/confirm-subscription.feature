@ignore
Feature: confirm subscription

Scenario: Retrieve subscriber
Given url subscriberReadUrl
And path 'name/' + 'Mandar' + '/' + 'Suresh' + '/' + 'Kulkarni' + '/' + 'Mr'
When method get
Then status 200

* def subscriberId = response.subscriberId

Scenario: select payment scheme
Given url platformSubscriberUrl
And path 'confirmsubscription/' + subscriberId
When method put
Then status 200
