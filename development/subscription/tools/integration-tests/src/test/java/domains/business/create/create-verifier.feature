@ignore
Feature: verify business account creation on read side for the financial year
# print %%%%IN CREATE VERIFIER%%%%%%%
Scenario: post the business account
* def creator = call read('./create/create-business.feature')

Scenario: verify business account from read side
* def readResult = call read('./create/businessaccount_read.feature')

