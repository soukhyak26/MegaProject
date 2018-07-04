@ignore
Feature: Create New Payment Scheme
Background:
* url   platformPaymentsUrl

Scenario:
Given url platformPaymentsUrl
And path 'payments/scheme'
And request read('classpath:domains/payments/scheme/set-payment-scheme.json')
When method post
Then status 201
