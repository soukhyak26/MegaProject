@ignore
Feature: verify business account  creation and configuration on read side for the financial year

Scenario: post the business account
* def result1 = call read('classpath:domains/business/create/create-verifier.feature')

Scenario: verify business account configuration from read side
* def result2 = call read('classpath:domains/business/configure/configure-verifier.feature')

Scenario: set purchase cost budget(manually) for a financial year
* def result3 = call read('classpath:domains/business/purchasecost/budget-purchasecost-verifier.feature')

Scenario: set benefit budget(manually) for a financial year
* def result4 = call read('classpath:domains/business/benefits/budget-benefit-verifier.feature')

Scenario: set taxes budget(manually) for a financial year
* def result4 = call read('classpath:domains/business/taxes/budget-taxes-verifier.feature')