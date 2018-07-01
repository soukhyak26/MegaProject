@ignore
Feature: read subscription rules configured

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:
Given url subscriberReadUrl
And path 'subscriber/subscription/rules'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriptionrules/readresponse.json')

