@ignore
Feature: configure business account for the financial year

Scenario:
Given url platformBusinessUrl
And path 'business/configure'
And request read('classpath:domains/business/configure/configurerequest.json')
When method post
Then status 201
