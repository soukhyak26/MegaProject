@ignore
Feature: create manual product forecast for the financial year

Scenario:
Given url productReadUrl
And path 'product/name/Jemini Refined Oil'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast2/createproductresponse.json')

* def productId1 = response[0].productId

Scenario:
Given url platformProductUrl
And path 'forecast/manual/' + productId1
And request read('classpath:domains/product/manualforecast2/create-forecast.json')
When method post
Then status 201
