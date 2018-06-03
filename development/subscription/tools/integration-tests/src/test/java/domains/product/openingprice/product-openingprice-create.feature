@ignore
Feature: manual set openining price for registered product

Scenario:
Given url platformProductUrl
And path 'product/name/Colgate 300 gms'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/createproductresponse.json')

Scenario:
Given url platformProductUrl
And path 'pricing/openprice/' + response[0].productId
And request read('classpath:domains/product/openingprice/set-openingprice.json')
When method post
Then status 201
