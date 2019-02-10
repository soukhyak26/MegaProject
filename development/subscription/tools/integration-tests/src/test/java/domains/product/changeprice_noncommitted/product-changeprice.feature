@ignore
Feature: manual change price of a non committed product

Scenario:
Given url productReadUrl
And path 'product/name/Jemini Refined Oil'
When method get
Then status 200

* def productId1 = response[0].productId

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:
Given url platformProductUrl
And path 'pricing/change/' + productId1
And request read('classpath:domains/product/changeprice_noncommitted/change-price.json')
When method post
Then status 201


