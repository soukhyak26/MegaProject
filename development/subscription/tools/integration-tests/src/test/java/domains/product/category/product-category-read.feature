@ignore
Feature: read product categories configured

Background:
* url productReadUrl
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario

* def expectedResponse = read('classpath:domains/product/category/product_categories_response.json')

Scenario:
Given url productReadUrl
And path 'product/categories'
And header Accept = 'application/json'
When method get
Then status 200
And match response == expectedResponse
