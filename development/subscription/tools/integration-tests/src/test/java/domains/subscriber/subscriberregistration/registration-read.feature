@ignore
Feature: read subscriber from read side

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:
Given url subscriberReadUrl
And path 'subscriber/' + id
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriberregistration/read-subscriber.json')
