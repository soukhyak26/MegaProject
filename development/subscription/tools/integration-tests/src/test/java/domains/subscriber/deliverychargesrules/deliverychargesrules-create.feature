@ignore
Feature: create delivery charges rules

Scenario:
Given url platformSubscriberUrl
And path 'deliverychargerules'
And request read('classpath:domains/subscriber/deliverychargesrules/createrequest.json')
When method post
Then status 201
