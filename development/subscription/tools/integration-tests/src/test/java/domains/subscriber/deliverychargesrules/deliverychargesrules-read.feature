@ignore
Feature: read delivery charges rules configured

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:
Given url subscriberReadUrl
And path 'subscriber/deliverycharge/rules'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/deliverychargesrules/readresponse.json')
