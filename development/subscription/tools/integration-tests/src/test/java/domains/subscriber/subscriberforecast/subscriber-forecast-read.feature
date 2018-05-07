@ignore
Feature: read product forecast

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario

#Forecast on Subscriber Read side
Scenario:
Given url subscriberReadUrl
And path 'subscriber/forecast/'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriberforecast/read-subscriber-forecast.json')

#PseudoActuals on Subscriber Read side
Scenario:
Given url subscriberReadUrl
And path 'subscriber/pseudoactuals/'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriberforecast/read-subscriber-pseudoactuals.json')


#Updated Product Activation status
#Scenario:
#Given url productReadUrl
#And path 'product/activationstatus/' + __arg.productId
#And header Accept = 'application/json'
#When method get
#Then status 200
#And match response == read('classpath:domains/product/manualforecast/createproductactivationstatusresponse.json')


