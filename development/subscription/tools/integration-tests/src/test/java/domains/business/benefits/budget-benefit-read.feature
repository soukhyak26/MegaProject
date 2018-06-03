@ignore
Feature: read provision for benefits budget

Scenario: introduce wait time
* call read(classpath:domains/common/introduce-wait-cycles.feature')

Scenario: read benefit budget
Given url businessReadUrl
And path 'business/benefitaccount/' + __arg.id
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/business/benefits/read-budget-benefit.json')
