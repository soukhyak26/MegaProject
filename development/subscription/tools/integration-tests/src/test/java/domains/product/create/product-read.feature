@ignore
Feature: read product registered

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:
Given url productReadUrl
And path 'product/name/Colgate 300 gms'
When method get
Then status 200

* def productId1 = response[0].productId

Scenario:
Given url productReadUrl
And path 'product/name/Lux 200 gms'
When method get
Then status 200

* def productId2 = response[0].productId

Scenario:
Given url productReadUrl
And path 'product/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductresponse.json')

Scenario:
Given url productReadUrl
And path 'product/' + productId2
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductresponse2.json')

Scenario:
Given url productReadUrl
And path 'product/taggedprice/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createtaggedpriceresponse1.json')

Scenario:
Given url productReadUrl
And path 'product/taggedprice/' + productId2
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createtaggedpriceresponse2.json')

Scenario:
Given url productReadUrl
And path 'product/activationstatus/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductactivationstatusresponse.json')

Scenario:
Given url productReadUrl
And path 'product/activationstatus/' + productId2
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductactivationstatusresponse.json')

Scenario:
Given url productReadUrl
And path 'product/count/' + '1'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/productanalyserview.json')

Scenario:
Given url productReadUrl
And path 'product/inventory/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/productinventoryview.json')

Scenario:
Given url productReadUrl
And path 'product/inventory/' + productId2
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/productinventoryview.json')

Scenario:
Given url businessReadUrl
And path 'business/businessaccount'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/businessaccount.json')

Scenario:
Given url businessReadUrl
And path 'business/product/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductresponse_business.json')

Scenario:
Given url businessReadUrl
And path 'business/product/' + productId2
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductresponse_business2.json')

Scenario:
Given url paymentsReadUrl
And path 'payments/taggedprice/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createtaggedpriceresponse_payments.json')

Scenario:
Given url paymentsReadUrl
And path 'payments/taggedprice/' + productId2
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createtaggedpriceresponse_payments2.json')

Scenario:
Given url paymentsReadUrl
And path 'payments/product/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductresponse_payments.json')

Scenario:
Given url paymentsReadUrl
And path 'payments/product/' + productId2
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductresponse_payments2.json')
