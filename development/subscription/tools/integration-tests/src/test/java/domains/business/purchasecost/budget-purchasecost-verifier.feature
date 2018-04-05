@ignore
Feature: verify purchase cost budget setting
# print %%%%IN PURCHASE COST BUDGET VERIFIER%%%%%%%
Scenario: post the business account
* def setResult = call read('classpath:domains/business/purchasecost/set-budget-purchasecost.feature')

Scenario: verify business account from read side
* def readResult = call read('classpath:domains/business/purchasecost/read-budget-purchasecost.feature') $setResult

