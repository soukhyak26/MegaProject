@ignore
Feature: Trigger Ordering batch

Scenario:
Given url fulfillmentReadUrl
And path '/fulfillment/feedorders'
And request {}
When method post
Then status 200
