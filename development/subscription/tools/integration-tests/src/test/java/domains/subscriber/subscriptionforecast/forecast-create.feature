@ignore
Feature: create manual subscriber forecast for the financial year

Scenario:
Given url platformSubscriberUrl
And path 'subscriber/forecast/manual/subscriber'
And request read('classpath:domains/subscriber/subscriberforecast/create-forecast.json')
When method post
Then status 201
