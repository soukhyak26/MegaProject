@ignore
Feature: verify taxes budget setting
# print %%%%IN TAXES BUDGET VERIFIER%%%%%%%
Scenario: set budget for taxes
* def setResult = call read('classpath:domains/business/taxes/set-budget-taxes.feature')

Scenario: verify taxes budget from read side
* def readResult = call read('classpath:domains/business/taxes/read-budget-taxes.feature') $setResult

