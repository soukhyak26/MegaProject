@ignore
Feature: Delivery dispatching and its verification

Scenario: Status of entities in different domains PRIOR to dispatch
* call read('classpath:domains/deliverydispatch/delivery-dispatch-read-before.feature')

Scenario: Delivery Dispatch
* call read('classpath:domains/deliverydispatch/trigger-deliverydispatch-batch.feature')

Scenario: Status of entities in different domains AFTER dispatch
* call read('classpath:domains/deliverydispatch/delivery-dispatch-read-after.feature')
