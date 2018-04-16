@ignore
Feature: verify benefits budget setting
# print %%%%IN BENEFITS BUDGET VERIFIER%%%%%%%
Scenario: set budget for benefits
* def setBenefitsBudgetResult = call read('classpath:domains/business/benefits/budget-benefit-create.feature')

Scenario: verify benefits budget from read side
* call read('classpath:domains/business/benefits/budget-benefit-read.feature') setBenefitsBudgetResult.response

