@ignore
Feature: create manual product forecast for the financial year

Scenario:
Given url productReadUrl
And path 'product/name/Colgate 300 gms'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/createproductresponse.json')

* def productId1 = response[0].productId
Scenario:
Given url platformProductUrl
And path 'forecast/manual/' + productId1
And request read('classpath:domains/product/manualforecast/create-forecast.json')
When method post
Then status 201

Scenario:
Given url productReadUrl
And path 'product/name/Lux 200 gms'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/createproductresponse2.json')

* def productId2 = response[0].productId
Scenario:
Given url platformProductUrl
And path 'forecast/manual/' + productId2
And request read('classpath:domains/product/manualforecast/create-forecast2.json')
When method post
Then status 201
