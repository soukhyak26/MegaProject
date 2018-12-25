@ignore
Feature: Decrement sys date

Scenario: Decrement sys date to the initial days of a financial year
Given url platformSysDateUrl
And path 'sysdate/reset/previous/days'
And request read('classpath:domains/common/update-sysdate.json')
When method put
Then status 200
