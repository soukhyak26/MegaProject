@ignore
Feature: read benefit equations

Background:
#* url businessReadUrl
* def afterScenario = function(){ karate.log('sleeping ..'); java.lang.Thread.sleep(3000); }

Scenario:
#Introduce delay
* call afterScenario


Given url benefitsReadUrl
And path 'benefits/' + __arg.benefitId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/benefits/read-benefit-equation.json')


Given url subscriberReadUrl
And path 'subscriber/benefits/schemes'
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/benefits/read-benefit-equation-subscriber.json')
