@ignore
Feature: read product registered

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario

Scenario:
Given url productReadUrl
And path 'product/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductresponse.json')

Scenario:
Given url productReadUrl
And path 'product/taggedprice/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createtaggedpriceresponse.json')

Scenario:
Given url productReadUrl
And path 'product/activationstatus/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductactivationstatusresponse.json')

Scenario:
Given url businessReadUrl
And path 'business/product/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductresponse_business.json')

Scenario:
Given url paymentsReadUrl
And path 'payments/taggedprice/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createtaggedpriceresponse_payments.json')

Scenario:
Given url paymentsReadUrl
And path 'payments/product/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductresponse_payments.json')
