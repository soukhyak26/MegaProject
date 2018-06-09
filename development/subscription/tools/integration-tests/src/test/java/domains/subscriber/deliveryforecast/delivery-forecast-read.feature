@ignore
Feature: read delivery forecast

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

#Forecast on Delivery Read side
Scenario:
Given url subscriberReadUrl
And path 'subscriber/deliveries/forecast'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/deliveryforecast/read-delivery-forecast.json')

* def weightRangeMin = response[0].deliveryForecastVersionId.weightRangeMin
* def weightRangeMax = response[0].deliveryForecastVersionId.weightRangeMax

#PseudoActuals on Delivery Read side
Scenario:
Given url subscriberReadUrl
And path 'subscriber/deliveries/pseudoactuals'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/deliveryforecast/read-delivery-pseudoactuals.json')

#Delivery Forecast on Business domain read side
Scenario:
Given url businessReadUrl
And path 'business/delivery/forecast/' + weightRangeMin + '/' + weightRangeMax
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/deliveryforecast/read-delivery-forecast-business.json')

#Obtain business account Id to be fed to the next scenario
Scenario:
Given url businessReadUrl
And path 'business/businessaccount'
And header Accept = 'application/json'
When method get
Then status 200

* def activeBusinessAccountId = response.businessAccountId

#Modification to Variable Expense on Business domain read side
Scenario:
Given url businessReadUrl
And path 'business/variableexpense/' + activeBusinessAccountId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/deliveryforecast/read-variableexpense-business.json')

#Modification to Variable Expense provision calendar on Business domain read side
Scenario:
Given url businessReadUrl
And path 'business/variableexpense/provision'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/deliveryforecast/read-variableexpense-provisioncalendar-business.json')

#Updated Product Activation status
#Scenario:
#Given url productReadUrl
#And path 'product/activationstatus/' + __arg.productId
#And header Accept = 'application/json'
#When method get
#Then status 200
#And match response == read('classpath:domains/product/manualforecast/createproductactivationstatusresponse.json')


