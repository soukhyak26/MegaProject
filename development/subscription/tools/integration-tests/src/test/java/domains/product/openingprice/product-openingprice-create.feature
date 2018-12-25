@ignore
Feature: manual set openining price for registered product

Scenario:
Given url productReadUrl
And path 'product/name/Colgate 300 gms'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/createproductresponse.json')

* def productId1 = response[0].productId

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:
Given url platformProductUrl
And path 'pricing/openprice/' + productId1
And request read('classpath:domains/product/openingprice/set-openingprice.json')
When method post
Then status 201

Scenario:
Given url productReadUrl
And path 'product/name/Lux 200 gms'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/createproductresponse2.json')

* def productId2 = response[0].productId

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:
Given url platformProductUrl
And path 'pricing/openprice/' + productId2
And request read('classpath:domains/product/openingprice/set-openingprice2.json')
When method post
Then status 201
