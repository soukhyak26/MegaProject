@ignore
Feature: configure business account for the financial year
Background:
* url   platformUrl

Scenario:
Given url platformUrl
And path 'business/configure'
And request read('./configure/configurerequest.json')
When method post
Then status 200
