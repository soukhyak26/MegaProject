@ignore
Feature: read product registered

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:
Given url productReadUrl
And path 'product/' + productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductresponse.json')

Scenario:
Given url productReadUrl
And path 'product/taggedprice/' + productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createtaggedpriceresponse.json')

Scenario:
Given url productReadUrl
And path 'product/activationstatus/' + productId
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
Given url businessReadUrl
And path 'business/businessaccount'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/businessaccount.json')

Scenario:
Given url businessReadUrl
And path 'business/product/' + productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductresponse_business.json')

Scenario:
Given url paymentsReadUrl
And path 'payments/taggedprice/' + productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createtaggedpriceresponse_payments.json')

Scenario:
Given url paymentsReadUrl
And path 'payments/product/' + productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/create/createproductresponse_payments.json')
