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
And match response == read('classpath:domains/product/openingprice2/read-openingprice-product.json')

Scenario: read opening price from subscriber
Given url subscriberReadUrl
And path 'subscriber/pricebucket/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/openingprice2/read-openingprice-subscriber.json')

Scenario: read opening price from payments
Given url paymentsReadUrl
And path 'payments/offerprice/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/openingprice2/read-openingprice-payments.json')
