@ignore
Feature:payment scheme setting
Scenario: set payment scheme
* def setPaymentSchemeResult = call read('classpath:domains/payments/scheme/payment-scheme-create.feature')

Scenario: verify payment scheme from read side
* call read('classpath:domains/payments/scheme/payment-scheme-read.feature') setPaymentSchemeResult.response

