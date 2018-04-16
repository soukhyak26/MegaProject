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

Scenario:verify business account creation and configuration on read side for the financial year
* call read('classpath:domains/business/business-verifier.feature')

Scenario:verify product category creation and product creation and configuration on read side
* call read('classpath:domains/product/product-verifier.feature')