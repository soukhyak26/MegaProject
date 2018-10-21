@ignore
Feature: Subscription creation and verification

Scenario: Subscription creation
* def subscriptionRegistrationResult = call read('classpath:domains/subscriber/subscriptionregistration/subscription-create.feature')

Scenario: add Item to subscription
* call read('classpath:domains/subscriber/subscriptionregistration/subscription-add-items.feature')

Scenario: add shipping address
* call read('classpath:domains/subscriber/subscriptionregistration/subscription-add-shipping-address.feature')

Scenario: add billing address
* call read('classpath:domains/subscriber/subscriptionregistration/subscription-add-billing-address.feature')

Scenario: add contact details
* call read('classpath:domains/subscriber/subscriptionregistration/subscription-add-contact-details.feature')

Scenario: select payment scheme
* call read('classpath:domains/subscriber/subscriptionregistration/select-payment-scheme.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: confirm subscription
* call read('classpath:domains/subscriber/subscriptionregistration/confirm-subscription.feature')

Scenario: Subscription validation
* call read('classpath:domains/subscriber/subscriptionregistration/subscription-read.feature') subscriptionRegistrationResult.response

Scenario: Payment installment validation
* call read('classpath:domains/subscriber/subscriptionregistration/payment-installment-read.feature') subscriptionRegistrationResult.response

Scenario: PriceBucket update validation
* call read('classpath:domains/subscriber/subscriptionregistration/pricebuckets-read.feature') subscriptionRegistrationResult.response