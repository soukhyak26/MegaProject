@ignore
Feature: Payment and its verification

Scenario: Make Payment
* def subscriptionRegistrationResult = call read('classpath:domains/payments/makepayment/make-payment-create.feature')

Scenario: Confirm Payment
* call read('classpath:domains/payments/makepayment/make-payment-read.feature')

