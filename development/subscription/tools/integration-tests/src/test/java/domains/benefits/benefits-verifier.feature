@ignore
Feature: verify benefits equation setting
# print %%%%IN BENEFITS VERIFIER%%%%%%%
Scenario: set benefits
* def setBenefitsResult = call read('classpath:domains/benefits/benefit-create.feature')

* print setBenefitsResult.response

Scenario: verify benefits from read side
* call read('classpath:domains/benefits/benefit-read.feature') setBenefitsResult.response

