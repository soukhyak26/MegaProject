@ignore
Feature: verify business account configuration on read side for the financial year

Scenario: post the business account
* call read('classpath:domains/business/configure/businessaccount-configure.feature')

Scenario: verify business account configuration from read side
* call read('classpath:domains/business/configure/businessaccount-configure-read.feature')
