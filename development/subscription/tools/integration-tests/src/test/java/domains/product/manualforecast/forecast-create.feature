@ignore
Feature: create manual product forecast for the financial year

Scenario:
Given url platformProductUrl
And path 'product/name/Colgate 300 gms'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/createproductresponse.json')

Scenario:
Given url platformProductUrl
And path 'forecast/manual/' + response[0].productId
And request read('classpath:domains/product/manualforecast/create-forecast.json')
When method post
Then status 201
