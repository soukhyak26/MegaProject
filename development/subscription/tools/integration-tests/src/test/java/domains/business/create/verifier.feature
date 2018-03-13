
Feature: verify business account creation on read side for the financial year
Background:
* url   demoBaseUrl


#Scenario: post the business account

* def result = call read('create-business1.feature')

* url demoReadUrl
* print demoReadUrl
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }
Scenario:
* call afterScenario

Given url demoReadUrl
And path 'business/businessaccount'
And header Accept = 'application/json'
When method get
Then status 200
And match response == {"id":"#ignore","merchantId":"merchant1","startDate":[2018,1,1],"endDate":[2018,12,31],"dateForProvision":[2018,1,1],"defaultPercentFixedExpensePerUnitPrice":"#ignore","defaultPercentVariableExpensePerUnitPrice":"#ignore"}

