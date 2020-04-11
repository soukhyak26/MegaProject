@ignore
Feature: Decrement sys date
Scenario: Reset sys date to the current date
Given url platformSysDateUrl
And path 'sysdate/reset'
When method get
Then status 200

