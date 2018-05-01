@ignore
Feature: read subscription rules configured

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario

Scenario:
Given url subscriberReadUrl
And path 'subscriber/subscription/rules'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriptionrules/readresponse.json')
