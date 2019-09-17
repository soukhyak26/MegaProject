Feature: integration test suite covering operations in all the domains
* configure readTimeout = 60000
* configure connectTimeout = 60000

Scenario: Decrement sys date
* call read('classpath:domains/common/decrement-sysdate.feature')

Scenario:Subscriber initial setup and verification
* call read('classpath:domains/subscriber/subscriber-initial-setup-verifier.feature')

Scenario:verify business account creation and configuration on read side for the financial year
* call read('classpath:domains/business/business-verifier.feature')






