@ignore
Feature: read provision for others budget

Scenario: introduce wait time
* call read(classpath:domains/common/introduce-wait-cycles.feature')

* def expectedProvisionResponse = read('classpath:domains/business/others/read-budget-others.json')

Scenario: validate budget for others from read side
Given url businessReadUrl
And path 'business/others/' +__arg.id
When method get
Then status 200
And match response == expectedProvisionResponse
