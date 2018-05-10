@ignore
Feature: Delivery charges rules creation and verification

Scenario: Delivery charges rules creation
* call read('classpath:domains/subscriber/deliverychargesrules/deliverychargesrules-create.feature')

Scenario: Delivery charges rules validation
* call read('classpath:domains/subscriber/deliverychargesrules/deliverychargesrules-read.feature')
