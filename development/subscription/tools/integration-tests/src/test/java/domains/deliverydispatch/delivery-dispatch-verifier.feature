@ignore
Feature: Delivery dispatching and its verification

Scenario: Delivery Dispatch
* call read('classpath:domains/deliverydispatch/trigger-deliverydispatch-batch.feature')

Scenario: Verification of the Dispatch in different domains
* call read('classpath:domains/deliverydispatch/delivery-dispatch-read.feature')
