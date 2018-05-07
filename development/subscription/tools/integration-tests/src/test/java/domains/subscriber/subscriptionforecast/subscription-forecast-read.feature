@ignore
Feature: read subscription forecast

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario

#Forecast on Subscription Read side
Scenario:
Given url subscriberReadUrl
And path 'subscriber/subscriptions/forecast'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriptionforecast/read-subscription-forecast.json')

#PseudoActuals on Subscription Read side
Scenario:
Given url subscriberReadUrl
And path 'subscriber/subscriptions/pseudoactuals'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriptionsforecast/read-subscription-pseudoactuals.json')


#Updated Product Activation status
#Scenario:
#Given url productReadUrl
#And path 'product/activationstatus/' + __arg.productId
#And header Accept = 'application/json'
#When method get
#Then status 200
#And match response == read('classpath:domains/product/manualforecast/createproductactivationstatusresponse.json')


