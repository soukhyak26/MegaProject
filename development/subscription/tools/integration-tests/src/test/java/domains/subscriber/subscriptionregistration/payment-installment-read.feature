@ignore
Feature: read the payment installments created due to subscription confirmation

Scenario: introduce wait time
* call read('classpath:domains/common/introduce-wait-cycles.feature')

Scenario:read payment installments
Given url paymentsReadUrl
And path 'payments/installments/' + id
And header Accept = 'application/json'
When method get
Then status 200
And match response == read('classpath:domains/subscriber/subscriptionregistration/read-paymentinstallments.json')
