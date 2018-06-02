@ignore
Feature: verify benefits equation setting
Scenario: set benefits
* def setBenefitsResult = call read('classpath:domains/benefits/benefit-create.feature')

Scenario: verify benefits from read side
* call read('classpath:domains/benefits/benefit-read.feature') setBenefitsResult.response

