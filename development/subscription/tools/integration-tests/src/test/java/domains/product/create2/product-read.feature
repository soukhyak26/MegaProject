@ignore
Feature: read product registered

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:
Given url productReadUrl
And path 'product/name/Jemini Refined Oil'
When method get
Then status 200

* def productId1 = response[0].productId


Scenario:
Given url productReadUrl
And path 'product/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create2/createproductresponse.json')


Scenario:
Given url productReadUrl
And path 'product/taggedprice/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create2/createtaggedpriceresponse1.json')

Scenario:
Given url productReadUrl
And path 'product/activationstatus/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create2/createproductactivationstatusresponse.json')

Scenario:
Given url productReadUrl
And path 'product/count/' + '1'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create2/productanalyserview.json')

Scenario:
Given url productReadUrl
And path 'product/inventory/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create2/productinventoryview.json')

Scenario:
Given url businessReadUrl
And path 'business/businessaccount'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create2/businessaccount.json')

Scenario:
Given url businessReadUrl
And path 'business/product/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create2/createproductresponse_business.json')

Scenario:
Given url paymentsReadUrl
And path 'payments/taggedprice/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create2/createtaggedpriceresponse_payments.json')

Scenario:
Given url paymentsReadUrl
And path 'payments/product/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create2/createproductresponse_payments.json')
