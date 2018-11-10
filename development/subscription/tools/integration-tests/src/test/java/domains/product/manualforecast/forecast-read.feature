@ignore
Feature: read product forecast

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

* def purchaseAccountValue = read('classpath:domains/product/manualforecast/read-purchaseaccount-business.json')

#Forecast on Product Read side
Scenario:
Given url productReadUrl
And path 'product/forecast/' + productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/read-forecast.json')

#PseudoActuals on Product Read side
Scenario:
Given url productReadUrl
And path 'product/pseudoactuals/' + productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/read-pseudoactuals.json')


#Updated Product Activation status
Scenario:
Given url productReadUrl
And path 'product/activationstatus/' + productId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/product/manualforecast/createproductactivationstatusresponse.json')

#Product Forecast on Business domain read side
Scenario:
Given url businessReadUrl
And path 'business/product/forecast/' + productId
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
