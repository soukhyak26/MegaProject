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

* def expectedResponseTaggedPrice = read('classpath:domains/product/create/createtaggedpriceresponse.json')
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

* def expectedResponseBusiness = read('classpath:domains/product/create/createproductresponse_business.json')
Scenario:
Given url businessReadUrl
And path 'business/product/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == expectedResponseBusiness

* def expectedTaggedPriceResponsePayments = read('classpath:domains/product/create/createtaggedpriceresponse_payments.json')
Scenario:
Given url paymentsReadUrl
And path 'payments/taggedprice/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == expectedTaggedPriceResponsePayments

* def expectedResponsePayments = read('classpath:domains/product/create/createproductresponse_payments.json')
Scenario:
Given url paymentsReadUrl
And path 'payments/product/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == expectedResponsePayments
