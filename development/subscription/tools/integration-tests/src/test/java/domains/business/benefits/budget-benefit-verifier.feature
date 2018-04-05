@ignore
Feature: verify benefits budget setting
# print %%%%IN BENEFITS BUDGET VERIFIER%%%%%%%
Scenario: set budget for benefits
* def setResult = call read('classpath:domains/business/benefits/set-budget-benefit.feature')

Scenario: verify benefits budget from read side
* def readResult = call read('classpath:domains/business/benefits/read-budget-benefit.feature') $setResult

