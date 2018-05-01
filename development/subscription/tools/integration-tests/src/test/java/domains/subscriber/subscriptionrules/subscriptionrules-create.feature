@ignore
Feature: create subscription rules

Scenario:
Given url platformSubscriberUrl
And path 'subscriptionrules'
And request read('classpath:domains/subscriber/subscriptionrules/createrequest.json')
When method post
Then status 201
