@ignore
Feature: read opening price of registered products

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: read opening price
Given url productReadUrl
And path 'product/pricebuckets/active/' + productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/openingprice/read-openingprice-product.json')

Scenario: read opening price from subscriber
Given url subscriberReadUrl
And path 'subscriber/pricebucket/' + productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/openingprice/read-openingprice-subscriber.json')

Scenario: read opening price from payments
Given url paymentsReadUrl
And path 'payments/offerprice/' + productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/openingprice/read-openingprice-payments.json')
