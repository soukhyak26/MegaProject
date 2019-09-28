@ignore
Feature: Subscription verification post modification of subscription

Scenario: add new subscription item
* call read('classpath:domains/subscriber/modifysubscriptions/subscription-add-items2.feature')

Scenario: verify subscription item
* call read('classpath:domains/subscriber/modifysubscriptions/subscription-read2.feature')

Scenario: verify price buckets
* call read('classpath:domains/subscriber/modifysubscriptions/pricebuckets-read2.feature')
