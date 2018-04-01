@ignore
Feature: read product registered

Background:
* url productReadUrl
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario

* def expectedResponse = read('classpath:domains/product/create/createproductresponse.json')
* print expectedResponse

Scenario:
Given url productReadUrl
And path 'product/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == expectedResponse

* def expectedResponseTaggedPrice = read('classpath:domains/product/create/createaggedpriceresponse.json')
Scenario:
Given url productReadUrl
And path 'product/taggedprice/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == expectedResponseTaggedPrice

* def expectedResponseActivationStatus = read('classpath:domains/product/create/createproductactivationstatusresponse.json')
Scenario:
Given url productReadUrl
And path 'product/activationstatus/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == expectedResponseActivationStatus
