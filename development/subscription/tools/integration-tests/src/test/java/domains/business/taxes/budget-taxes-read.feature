@ignore
Feature: read provision for taxes budget

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario

Given url businessReadUrl
And path 'business/taxesaccount/' + __arg.id
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/business/taxes/read-budget-taxes.json')
