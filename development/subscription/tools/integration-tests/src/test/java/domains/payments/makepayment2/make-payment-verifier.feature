@ignore
Feature: Payment and its verification

Scenario: Make Payment
* def subscriptionRegistrationResult = call read('classpath:domains/payments/makepayment2/make-payment-create.feature')

Scenario: Confirm Payment
* call read('classpath:domains/payments/makepayment2/make-payment-read.feature')
