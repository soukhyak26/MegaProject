@ignore
Feature: read product configuration

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario

Scenario:
Given url productReadUrl
And path 'product/configuration/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/config/readconfigresponse.json')