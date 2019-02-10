@ignore
Feature: read pricebuckets from product domain and validate if their  new and total subscription count is updated or not

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: Retrieve subscriber
Given url subscriberReadUrl
And path 'subscriber/name/' + 'Mandar' + '/' + 'Suresh' + '/' + 'Kulkarni' + '/' + 'Mr'
When method get
Then status 200

* def subscriberId = response.subscriberId

Scenario: Retrieve subscription Id given subscriber Id
Given url subscriberReadUrl
And path 'subscriber/subscription/active/subscriber/' + subscriberId
When method get
Then status 200

* def subscriptionId = response.subscriptionId

Scenario: read subscription view
Given url subscriberReadUrl
And path 'subscriber/subscription/' + subscriptionId
And header Accept = 'application/json'
When method get
Then status 200

* def subscriptionItems = response.subscriptionItems
* def creator = read('classpath:domains/subscriber/modifysubscription/loop-subscription-items2.feature')
* def result = call creator subscriptionItems
