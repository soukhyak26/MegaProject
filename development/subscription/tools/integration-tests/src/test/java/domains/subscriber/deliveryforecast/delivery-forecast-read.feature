@ignore
Feature: read delivery forecast

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario

#Forecast on Delivery Read side
Scenario:
Given url subscriberReadUrl
And path 'subscriber/deliveries/forecast'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/deliveryforecast/read-delivery-forecast.json')

#PseudoActuals on Delivery Read side
Scenario:
Given url subscriberReadUrl
And path 'subscriber/deliveries/pseudoactuals'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/deliveryforecast/read-delivery-pseudoactuals.json')


#Updated Product Activation status
#Scenario:
#Given url productReadUrl
#And path 'product/activationstatus/' + __arg.productId
#And header Accept = 'application/json'
#When method get
#Then status 200
#And match response == read('classpath:domains/product/manualforecast/createproductactivationstatusresponse.json')


