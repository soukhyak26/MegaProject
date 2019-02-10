@ignore
Feature: read product forecast

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

* def purchaseAccountValue = read('classpath:domains/product/manualforecast2/read-purchaseaccount-business.json')

Scenario:
Given url productReadUrl
And path 'product/name/Jemini Refined Oil'
When method get
Then status 200

* def productId1 = response[0].productId


#Forecast on Product Read side - Product1
Scenario:
Given url productReadUrl
And path 'product/forecast/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast2/read-forecast.json')

#PseudoActuals on Product Read side - Product1
Scenario:
Given url productReadUrl
And path 'product/pseudoactuals/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast2/read-pseudoactuals.json')

#Updated Product Activation status -Product1
Scenario:
Given url productReadUrl
And path 'product/activationstatus/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast2/createproductactivationstatusresponse.json')

#Product Forecast on Business domain read side -Product1
Scenario:
Given url businessReadUrl
And path 'business/product/forecast/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast2/read-forecast-business.json')

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
And path 'business/purchaseaccount/' + response.businessAccountId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast2/read-purchaseaccount-business.json')

#Purchase cost provision calendar on Business domain read side
Scenario:
Given url businessReadUrl
And path 'business/purchaseaccount/provision/' + response.businessAccountId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast2/read-purchase-provisioncalendar-business.json')
