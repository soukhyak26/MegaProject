@ignore
Feature: manual set budget for taxes for the financial year

Scenario:
Given url platformBusinessUrl
And path 'business/provision/taxes'
And request read('classpath:domains/business/taxes/set-budget-taxes.json')
When method post
Then status 201

