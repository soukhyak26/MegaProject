@ignore
Feature: verify business account  creation and configuration on read side for the financial year

Scenario: post the business account
* call read('classpath:domains/business/create/create-businessaccount-verifier.feature')

Scenario: verify business account configuration from read side
* call read('classpath:domains/business/configure/configure-businessaccount-verifier.feature')

Scenario: set purchase cost budget(manually) for a financial year
* call read('classpath:domains/business/purchasecost/budget-purchasecost-verifier.feature')

Scenario: set benefit budget(manually) for a financial year
* call read('classpath:domains/business/benefits/budget-benefit-verifier.feature')

Scenario: set taxes budget(manually) for a financial year
* call read('classpath:domains/business/taxes/budget-taxes-verifier.feature')