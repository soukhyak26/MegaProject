@ignore
Feature: Trigger Ordering batch

Scenario:
Given url platformFulfillmentUrl
And path 'batch/deliver/triggerorder'
And request {}
When method post
Then status 200
