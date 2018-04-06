Feature: integration test suite covering operations in all the domains

Scenario:verify business account creation and configuration on read side for the financial year
* def businessResult = call read('classpath:domains/business/business-verifier.feature')

Scenario:verify product category creation and product creation and configuration on read side
* def productResult = call read('classpath:domains/product/product-verifier.feature')