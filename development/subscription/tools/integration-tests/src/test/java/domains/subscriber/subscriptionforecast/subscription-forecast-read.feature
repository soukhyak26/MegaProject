@ignore
Feature: read subscription forecast

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

#Forecast on Subscription Read side
Scenario:
Given url subscriberReadUrl
And path 'subscriber/subscriptions/forecast'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriptionforecast/read-subscription-forecast.json')

* def valueRangeMin = response[0].subscriptionVersionId.valueRangeMin
* def valueRangeMax = response[0].subscriptionVersionId.valueRangeMax

#PseudoActuals on Subscription Read side
Scenario:
Given url subscriberReadUrl
And path 'subscriber/subscriptions/pseudoactuals'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriptionforecast/read-subscription-pseudoactuals.json')

#Subscription Forecast on Business domain read side
Scenario:
Given url businessReadUrl
And path 'business/subscription/forecast/' + valueRangeMin + '/' + valueRangeMax
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriptionforecast/read-subscription-forecast-business.json')


#Obtain business account Id to be fed to the next scenario
Scenario:
Given url businessReadUrl
And path 'business/businessaccount'
And header Accept = 'application/json'
When method get
Then status 200

* def activeBusinessAccountId = response.id

#Modification to Taxes Account on Business domain read side
Scenario:
Given url businessReadUrl
And path 'business/taxesaccount/' + response.businessAccountId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriptionforecast/read-taxes-business.json')

#Modification to Taxes provision calendar on Business domain read side
Scenario:
Given url businessReadUrl
And path 'business/taxesaccount/provision'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriptionforecast/read-taxes-provisioncalendar-business.json')

#Updated Product Activation status
#Scenario:
#Given url productReadUrl
#And path 'product/activationstatus/' + productId
#And header Accept = 'application/json'
#When method get
#Then status 200
#And match response == read('classpath:domains/product/manualforecast/createproductactivationstatusresponse.json')


