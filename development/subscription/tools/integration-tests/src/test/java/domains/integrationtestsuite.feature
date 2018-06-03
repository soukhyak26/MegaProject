Feature: integration test suite covering operations in all the domains

Background:
* url   platformProductUrl
* url   productReadUrl
* url   platformBusinessUrl
* url   businessReadUrl
* url   platformSubscriberUrl
* url   subscriberReadUrl
* url   platformPaymentsUrl
* url   paymentsReadUrl

Scenario:Subscriber initial setup and verification
* call read('classpath:domains/subscriber/subscriber-initial-setup-verifier.feature')

Scenario:verify business account creation and configuration on read side for the financial year
* call read('classpath:domains/business/business-verifier.feature')

Scenario:verify product category creation and product creation and configuration on read side
* call read('classpath:domains/product/product-verifier.feature')

Scenario:subscriber manual forecast and verification
* call read('classpath:domains/subscriber/subscriberforecast/subscriber-forecast-verifier.feature')

Scenario:subscription manual forecast and verification
* call read('classpath:domains/subscriber/subscriptionforecast/subscription-forecast-verifier.feature')

Scenario:delivery manual forecast and verification
* call read('classpath:domains/subscriber/deliveryforecast/delivery-forecast-verifier.feature')

Scenario:Benefits creation and verification
* call read('classpath:domains/benefits/benefits-verifier.feature')

Scenario:Payment Scheme creation and verification
* call read('classpath:domains/payments/payment-scheme-verifier.feature')

Scenario:Product opening price setting and verification
* call read('classpath:domains/product/openingprice/product-openingprice-verifier.feature')