@ignore
Feature: register a subscriber

Scenario:
Given url platformSubscriberUrl
And path 'subscriber'
And request read('classpath:domains/subscriber/subscriberregistration/create-subscriber.json')
When method post
Then status 201
