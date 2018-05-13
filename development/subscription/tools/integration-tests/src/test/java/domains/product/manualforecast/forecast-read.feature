@ignore
Feature: read product forecast

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario
* def purchaseAccountValue = read('classpath:domains/product/manualforecast/read-purchaseaccount-business.json')

#Forecast on Product Read side
Scenario:
Given url productReadUrl
And path 'product/forecast/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/read-forecast.json')

#PseudoActuals on Product Read side
Scenario:
Given url productReadUrl
And path 'product/pseudoactuals/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/read-pseudoactuals.json')


#Updated Product Activation status
Scenario:
Given url productReadUrl
And path 'product/activationstatus/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/createproductactivationstatusresponse.json')

#Product Forecast on Business domain read side
Scenario:
Given url businessReadUrl
And path 'business/product/forecast/' + __arg.productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/read-forecast-business.json')

#Obtain business account Id to be fed to the next scenario
Scenario:
Given url businessReadUrl
And path 'business/businessaccount'
And header Accept = 'application/json'
When method get
Then status 200

#Purchase cost account on Business domain read side
Scenario:
Given url businessReadUrl
And path 'business/purchaseaccount/' + response.id
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/read-purchaseaccount-business.json')
