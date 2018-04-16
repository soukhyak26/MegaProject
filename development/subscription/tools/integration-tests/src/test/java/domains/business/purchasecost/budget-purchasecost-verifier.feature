@ignore
Feature: verify purchase cost budget setting

Scenario: post the business account
* def setPurchaseCostResult = call read('classpath:domains/business/purchasecost/budget-purchasecost-create.feature')

* print setPurchaseCostResult

Scenario: verify business account from read side
* call read('classpath:domains/business/purchasecost/budget-purchasecost-read.feature') setPurchaseCostResult.response

