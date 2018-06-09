@ignore
Feature: read benefit equations

Scenario: introduce wait time
* call read(classpath:domains/common/introduce-wait-cycles.feature')


Scenario: validate benefit scheme from read side
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
