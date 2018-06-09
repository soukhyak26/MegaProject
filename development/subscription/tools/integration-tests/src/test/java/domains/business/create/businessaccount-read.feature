@ignore
Feature: create business account for the financial year

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Given url businessReadUrl
And path 'business/businessaccount'
And header Accept = 'application/json'
When method get
Then status 200
And match response == {"id":"#ignore","merchantId":"merchant1","startDate":[2018,1,1],"endDate":[2018,12,31],"dateForProvision":[2018,1,1],"defaultPercentFixedExpensePerUnitPrice":"#ignore","defaultPercentVariableExpensePerUnitPrice":"#ignore"}
