@ignore
Feature: read product registered

Background:
* url productReadUrl
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario

* def expectedResponse = read('classpath:domains/product/create/createproductresponse.json')

Scenario:
Given url productReadUrl
And path 'product/#(productId)'
And header Accept = 'application/json'
When method get
Then status 200
And match response == expectedResponse
