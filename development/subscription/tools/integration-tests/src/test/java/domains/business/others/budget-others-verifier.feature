@ignore
Feature: verify others budget setting

Scenario: post the business account
* def setOthersResult = call read('classpath:domains/business/others/budget-others-create.feature')

* print setOthersResult.response
Scenario: verify business account from read side
* call read('classpath:domains/business/others/budget-others-read.feature') setOthersResult.response

