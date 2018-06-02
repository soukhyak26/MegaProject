@ignore
Feature: read payment scheme

Background:
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario


Given url paymentsReadUrl
And path 'payments/scheme/' + __arg.schemeId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/payments/read-payment-scheme.json')


Given url subscriberReadUrl
And path 'subscriber/payments/scheme/' + __arg.schemeId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/payments/read-payment-scheme.json')
