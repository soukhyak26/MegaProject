@ignore
Feature: read provision for others budget

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario
* def expectedProvisionResponse = read('classpath:domains/business/others/read-budget-others.json')

Given url businessReadUrl
And path 'business/others/' +__arg.id
When method get
Then status 200
And match response == expectedProvisionResponse
