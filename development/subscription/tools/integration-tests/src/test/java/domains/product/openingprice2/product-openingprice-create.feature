@ignore
Feature: manual set openining price for registered product

Scenario:
Given url productReadUrl
And path 'product/name/Jemini Refined Oil'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast2/createproductresponse.json')

* def productId1 = response[0].productId

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:
Given url platformProductUrl
And path 'pricing/openprice/' + productId1
And request read('classpath:domains/product/openingprice2/set-openingprice.json')
When method post
Then status 201

