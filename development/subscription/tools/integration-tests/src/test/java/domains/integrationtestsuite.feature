Feature: integration test suite covering operations in all the domains
Scenario: Decrement sys date
* call read('classpath:domains/common/decrement-sysdate.feature')

Scenario:Subscriber initial setup and verification
* call read('classpath:domains/subscriber/subscriber-initial-setup-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:verify business account creation and configuration on read side for the financial year
* call read('classpath:domains/business/business-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:verify product category creation and product creation and configuration on read side
* call read('classpath:domains/product/product-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:subscriber manual forecast and verification
* call read('classpath:domains/subscriber/subscriberforecast/subscriber-forecast-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:subscription manual forecast and verification
* call read('classpath:domains/subscriber/subscriptionforecast/subscription-forecast-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:delivery manual forecast and verification
* call read('classpath:domains/subscriber/deliveryforecast/delivery-forecast-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:Benefits creation and verification
* call read('classpath:domains/benefits/benefits-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:Payment Scheme creation and verification
* call read('classpath:domains/payments/scheme/payment-scheme-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:Product opening price setting and verification
* call read('classpath:domains/product/openingprice/product-openingprice-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:Subscriber registration and verification
* call read('classpath:domains/subscriber/subscriberregistration/registration-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:Subscription registration and verification
* call read('classpath:domains/subscriber/subscriptionregistration/subscription-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:Make payment for the first installment
* call read('classpath:domains/payments/makepayment/make-payment-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: Trigger Delivery dispatch batch
* call read('classpath:domains/deliverydispatch/delivery-dispatch-verifier.feature')

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: Modify subscription and verify
* call read('classpath:domains/subscriber/modifysubscription/modifysubscription-verifier.feature')