@ignore
Feature:payment scheme setting
Scenario: set payment scheme
* def setPaymentSchemeResult = call read('classpath:domains/payments/payment-scheme-create.feature')

Scenario: verify payment scheme from read side
* call read('classpath:domains/payments/payment-scheme-read.feature') setPaymentSchemeResult.response

