@ignore
Feature: manual set budget for others for the financial year

Scenario:
Given url platformBusinessUrl
And path 'business/provision/others'
And request read('classpath:domains/business/others/set-budget-others.json')
When method post
Then status 201

