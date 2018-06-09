@ignore
Feature: read provision for purchase budget

Scenario: introduce wait time
* call read(classpath:domains/common/introduce-wait-cycles.feature')


* def expectedProvisionResponse = read('classpath:domains/business/purchasecost/read-budget-purchasecost.json')

Scenario: validate purchase cost budget from read side
Given url businessReadUrl
And path 'business/purchaseaccount/' +__arg.businessAccountId
When method get
Then status 200
And match response == expectedProvisionResponse
