@ignore
Feature: verify business account configuration on read side for the financial year
# print %%%%IN CONFIGURE VERIFIER%%%%%%%
Scenario: post the business account
* def result = call read('./configure/configure-business.feature')

Scenario: verify business account configuration from read side
* def readResult = call read('./configure/businessaccount_read_configure.feature')

