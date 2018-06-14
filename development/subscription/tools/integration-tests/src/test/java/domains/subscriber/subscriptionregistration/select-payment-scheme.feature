@ignore
Feature: select payment scheme

Scenario: Retrieve subscriber
Given url subscriberReadUrl
And path 'name/' + 'Mandar' + '/' + 'Suresh' + '/' + 'Kulkarni' + '/' + 'Mr'
When method get
Then status 200

* def subscriberId = response.subscriberId

Scenario: Retrieve Payment Scheme
Given url paymentReadUrl
And path 'schemes'
When method get
Then status 200

* def schemeId = response[0].paymentSchemeId

Scenario: select payment scheme
Given url platformSubscriberUrl
And path 'selectpayment/' + subscriberId
And request '{paymentSchemeId : ' + schemeId + '}'
When method put
Then status 200
