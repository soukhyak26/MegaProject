@ignore
Feature: read delivery charges rules configured

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario

Scenario:
Given url subscriberReadUrl
And path 'subscriber/deliverycharge/rules'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/deliverychargesrules/readresponse.json')
