@ignore
Feature: manual set budget for purchase cost for the financial year

Scenario:
Given url platformBusinessUrl
And path 'business/provision/purchase'
And request read('classpath:domains/business/purchasecost/set-budget-purchasecost.json')
When method post
Then status 201

