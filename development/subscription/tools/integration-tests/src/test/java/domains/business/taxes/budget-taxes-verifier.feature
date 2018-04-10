@ignore
Feature: verify taxes budget setting
# print %%%%IN TAXES BUDGET VERIFIER%%%%%%%
Scenario: set budget for taxes
* def setResult = call read('classpath:domains/business/taxes/budget-taxes-create.feature')

Scenario: verify taxes budget from read side
* def readResult = call read('classpath:domains/business/taxes/budget-taxes-read.feature') $setResult

