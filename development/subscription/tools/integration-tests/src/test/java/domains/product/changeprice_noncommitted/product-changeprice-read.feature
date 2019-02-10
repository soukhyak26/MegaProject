@ignore
Feature: read opening price of registered products

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: red product details
Given url productReadUrl
And path 'product/name/Jemini Refined Oil'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast2/createproductresponse.json')

* def productId1 = response[0].productId

Scenario: read opening price from product
Given url productReadUrl
And path 'product/pricebuckets/active/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/changeprice_noncommitted/read-changeprice-product.json')

Scenario: read opening price from subscriber
Given url subscriberReadUrl
And path 'subscriber/pricebucket/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/changeprice_noncommitted/read-changeprice-subscriber.json')

Scenario: read opening price from payments
Given url paymentsReadUrl
And path 'payments/offerprice/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/changeprice_noncommitted/read-changeprice-payments.json')


Scenario:
Given url productReadUrl
And path 'product/name/Lux 200 gms'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/createproductresponse2.json')

* def productId2 = response[0].productId

Scenario: read opening price
Given url productReadUrl
And path 'product/pricebuckets/active/' + productId2
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/openingprice/read-openingprice-product2.json')

Scenario: read opening price from subscriber
Given url subscriberReadUrl
And path 'subscriber/pricebucket/' + productId2
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/openingprice/read-openingprice-subscriber2.json')

Scenario: read opening price from payments
Given url paymentsReadUrl
And path 'payments/offerprice/' + productId2
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/openingprice/read-openingprice-payments2.json')
