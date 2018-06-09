@ignore
Feature: read payment scheme

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario: validate payment scheme from read side of payments
Given url paymentsReadUrl
And path 'payments/scheme/' + __arg.schemeId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/payments/read-payment-scheme.json')

Scenario:validate payment scheme from read side of subscriber
Given url subscriberReadUrl
And path 'subscriber/payments/scheme/' + __arg.schemeId
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/payments/read-payment-scheme.json')
