Feature: verify business account  creation and configuration on read side for the financial year

Scenario: post the business account
* def result = call read('./create/create-verifier.feature')

Scenario: verify business account configuration from read side
* def readResult = call read('./configure/configure-verifier.feature')