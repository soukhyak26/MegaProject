@ignore
Feature: subscriber initial setup and verification


Scenario:Create subscription rules and verify the same
* call read('classpath:domains/subscriber/subscriptionrules/subscriptionrules-verifier.feature')

Scenario:Create delivery charges rules and verify the same
* call read('classpath:domains/subscriber/deliverychargesrules/deliverychargesrules-verifier.feature')