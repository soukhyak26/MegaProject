@ignore
Feature: read business account configured for the financial year

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: validate business configuration from read side
Given url businessReadUrl
And path 'business/businessaccount/configure'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/business/configure/readresponse.json')
