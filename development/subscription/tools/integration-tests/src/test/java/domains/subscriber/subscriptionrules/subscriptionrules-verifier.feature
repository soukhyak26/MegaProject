@ignore
Feature: Subscription rules creation and verification

Scenario: Subscription rules creation
* call read('classpath:domains/subscriber/subscriptionrules/subscriptionrules-create.feature')

Scenario: Subscription rules validation
* call read('classpath:domains/subscriber/subscriptionrules/subscriptionrules-read.feature')
