@ignore
Feature: create manual subscription forecast for the financial year

Scenario:
Given url platformSubscriberUrl
And path 'subscriber/forecast/manual/subscription'
And request read('classpath:domains/subscriber/subscriptionforecast/create-subscription-forecast.json')
When method post
Then status 201
