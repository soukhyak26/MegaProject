@ignore
Feature: create manual delivery forecast for the financial year

Scenario:
Given url platformSubscriberUrl
And path 'subscriber/forecast/manual/delivery'
And request read('classpath:domains/subscriber/deliveryforecast/create-delivery-forecast.json')
When method post
Then status 201
