@ignore
Feature: verify taxes budget setting

Scenario: set budget for taxes
* def setTaxesResult = call read('classpath:domains/business/taxes/budget-taxes-create.feature')

Scenario: verify taxes budget from read side
* call read('classpath:domains/business/taxes/budget-taxes-read.feature') setTaxesResult.response

