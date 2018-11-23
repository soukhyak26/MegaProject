@ignore
Feature: read product forecast

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

* def purchaseAccountValue = read('classpath:domains/product/manualforecast/read-purchaseaccount-business.json')

Scenario:
Given url productReadUrl
And path 'product/name/Colgate 300 gms'
When method get
Then status 200

* def productId1 = response.productId

Scenario:
Given url productReadUrl
And path 'product/name/Lux 200 gms'
When method get
Then status 200

* def productId2 = response.productId


#Forecast on Product Read side - Product1
Scenario:
Given url productReadUrl
And path 'product/forecast/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/read-forecast.json')

#Forecast on Product Read side - Product2
Scenario:
Given url productReadUrl
And path 'product/forecast/' + productId2
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/read-forecast.json')

#PseudoActuals on Product Read side - Product1
Scenario:
Given url productReadUrl
And path 'product/pseudoactuals/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/read-pseudoactuals.json')


#PseudoActuals on Product Read side - Product2
Scenario:
Given url productReadUrl
And path 'product/pseudoactuals/' + productId2
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/read-pseudoactuals.json')

#Updated Product Activation status -Product1
Scenario:
Given url productReadUrl
And path 'product/activationstatus/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/createproductactivationstatusresponse.json')

#Updated Product Activation status -Product2
Scenario:
Given url productReadUrl
And path 'product/activationstatus/' + productId2
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/createproductactivationstatusresponse.json')

#Product Forecast on Business domain read side -Product1
Scenario:
Given url businessReadUrl
And path 'business/product/forecast/' + productId1
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/read-forecast-business.json')

#Product Forecast on Business domain read side -Product2
Scenario:
Given url businessReadUrl
And path 'business/product/forecast/' + productId2
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
And path 'business/purchaseaccount/' + response.businessAccountId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/read-purchaseaccount-business.json')

#Purchase cost provision calendar on Business domain read side
Scenario:
Given url businessReadUrl
And path 'business/purchaseaccount/provision/' + response.businessAccountId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/read-purchase-provisioncalendar-business.json')
