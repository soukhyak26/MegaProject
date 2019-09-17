@ignore
Feature: create business account for the financial year

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

* print businessAccountStartDateFormatted
* print businessAccountEndDateFormatted

Given url businessReadUrl
And path 'business/businessaccount'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/business/create/readresponse.json')
