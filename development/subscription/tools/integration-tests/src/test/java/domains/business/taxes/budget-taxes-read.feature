@ignore
Feature: read provision for taxes budget

Scenario: introduce wait time
* call read(classpath:domains/common/introduce-wait-cycles.feature')

Scenario: validate taxes budget from read side
Given url businessReadUrl
And path 'business/taxesaccount/' + __arg.id
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/business/taxes/read-budget-taxes.json')
