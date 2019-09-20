Feature: integration test suite covering operations in all the domains
* configure readTimeout = 60000
* configure connectTimeout = 60000

Scenario: Decrement sys date
* call read('classpath:domains/common/decrement-sysdate.feature')

Scenario:Subscriber initial setup and verification
* call read('classpath:domains/subscriber/subscriber-initial-setup-verifier.feature')

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






