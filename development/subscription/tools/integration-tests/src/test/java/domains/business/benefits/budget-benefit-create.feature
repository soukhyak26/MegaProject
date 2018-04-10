@ignore
Feature: manual set budget for purchase cost for the financial year
Background:
* url   platformBusinessUrl

Scenario:
Given url platformBusinessUrl
And path 'business/provision/benefits'
And request read('classpath:domains/business/benefits/set-budget-benefit.json')
When method post
Then status 200

