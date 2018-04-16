@ignore
Feature: verify business account creation on read side for the financial year

Scenario: post the business account
* call read('classpath:domains/business/create/businessaccount-create.feature')

Scenario: verify business account from read side
* call read('classpath:domains/business/create/businessaccount-read.feature')

