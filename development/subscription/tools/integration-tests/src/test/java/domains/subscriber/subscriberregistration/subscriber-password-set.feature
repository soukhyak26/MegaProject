@ignore
Feature: set subscriber password

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: set subscriber password
Given url platformSubscriberUrl
And path 'subscriber/password/' + id
And request read('classpath:domains/subscriber/subscriberregistration/set-subscriber-password.json')
When method put
Then status 201