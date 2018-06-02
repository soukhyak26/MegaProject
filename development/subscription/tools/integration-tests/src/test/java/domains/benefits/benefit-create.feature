@ignore
Feature: Create New Benefits equation
Background:
* url   platformBenefitsUrl

Scenario:
Given url platformBenefitsUrl
And path 'benefits'
And request read('classpath:domains/benefits/set-benefit-equation.json')
When method post
Then status 201
