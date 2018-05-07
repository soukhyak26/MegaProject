@ignore
Feature: create delivery forecast for the financial year and verify if it  correctly created

Scenario: create delivery forecast
* call read('classpath:domains/subscriber/deliveryforecast/delivery-forecast-create.feature')

Scenario: verify delivery forecast creation
* call read('classpath:domains/subscriber/deliveryforecast/delivery-forecast-read.feature')
