@ignore
Feature: read provision for purchase budget

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario
* def expectedProvisionResponse = read('classpath:domains/business/purchasecost/read-budget-purchasecost.json')

Given url businessReadUrl
And path 'business/purchaseaccount/' +__arg.id
When method get
Then status 200
And match response == expectedProvisionResponse
