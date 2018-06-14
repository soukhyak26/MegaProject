@ignore
Feature: Subscription creation and verification

Scenario: Subscription creation
* def subscriptionRegistrationResult = call read('classpath:domains/subscriber/subscriptionregistration/subscription-create.feature')

Scenario : add Item to subscription
* call read('classpath:domains/subscriber/subscriptionregistration/subscription-add-items.feature')

Scenario : select payment scheme
* call read('classpath:domains/subscriber/subscriptionregistration/select-payment-scheme.feature')

Scenario : confirm subscription
* call read('classpath:domains/subscriber/subscriptionregistration/confirm-subscription.feature')

Scenario: Subscription validation
* call read('classpath:domains/subscriber/subscriptionregistration/subscription-read.feature') subscriptionRegistrationResult.response
